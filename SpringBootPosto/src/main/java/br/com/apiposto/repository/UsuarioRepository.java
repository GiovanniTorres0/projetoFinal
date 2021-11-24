package br.com.apiposto.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import br.com.apiposto.codec.UsuarioCodec;
import br.com.apiposto.modelo.Usuario;

@Repository
public class UsuarioRepository {

	private MongoClient cliente;
	private MongoDatabase bancaDeDados;

	private void criarConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

		UsuarioCodec usuarioCodec = new UsuarioCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(usuarioCodec));

		MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

		this.cliente = new MongoClient("localhost:27017", opcoes);
		this.bancaDeDados = cliente.getDatabase("test");

	}

	public void salvar(Usuario usuario) {

		criarConexao();
		MongoCollection<Usuario> usuarios = this.bancaDeDados.getCollection("usuarios", Usuario.class);
		if (usuario.getId() == null) {
			usuarios.insertOne(usuario);
		} else {
			usuarios.updateOne(Filters.eq("_id", usuario.getId()), new Document("$set", usuario));
		}

		fecharConexao();
	}

	private void fecharConexao() {
		this.cliente.close();
	}

	public List<Usuario> obterTodosUsuarios() {
		criarConexao();
		MongoCollection<Usuario> usuarios = this.bancaDeDados.getCollection("usuarios", Usuario.class);

		MongoCursor<Usuario> resultados = usuarios.find().iterator();

		List<Usuario> usuariosEncontrados = popularUsuarios(resultados);
		fecharConexao();

		return usuariosEncontrados;

	}

	private List<Usuario> popularUsuarios(MongoCursor<Usuario> resultados) {
		List<Usuario> usuarios = new ArrayList<>();
		while (resultados.hasNext()) {
			usuarios.add(resultados.next());
		}
		return usuarios;
	}

	public List<Usuario> pesquisaPorGeolocalizacao(Usuario usuario) {
		criarConexao();
		MongoCollection<Usuario> usuarioCollection = this.bancaDeDados.getCollection("usuarios", Usuario.class);

		usuarioCollection.createIndex(Indexes.geo2dsphere("ubicacion"));

		List<Double> coordinates = usuario.getUbicacion().getCoordenadas();
		Point pontoReferencia = new Point(new Position(coordinates.get(0), coordinates.get(1)));

		MongoCursor<Usuario> resultados = usuarioCollection
				.find(Filters.nearSphere("ubicacion", pontoReferencia, 2000.0, 0.0)).limit(2).skip(1).iterator();

		List<Usuario> usuarios = popularUsuarios(resultados);

		fecharConexao();
		return usuarios;
	}

	public Usuario obterUsuarioPor(String id) {
		criarConexao();
		MongoCollection<Usuario> usuarios = this.bancaDeDados.getCollection("usuarios", Usuario.class);
		Usuario usuario = usuarios.find(Filters.eq("_id", new ObjectId(id))).first();
		return usuario;
	}

	public List<Usuario> pesquisarPor(String nome) {
		criarConexao();
		MongoCollection<Usuario> usuarioCollection = this.bancaDeDados.getCollection("usuarios", Usuario.class);
		MongoCursor<Usuario> resultados = usuarioCollection.find(Filters.eq("nome", nome), Usuario.class).iterator();
		List<Usuario> usuarios = popularUsuarios(resultados);

		fecharConexao();

		return usuarios;
	}

}
