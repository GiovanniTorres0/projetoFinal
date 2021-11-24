package br.com.apiposto.repository;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Indexes;
import com.mongodb.client.model.geojson.Point;
import com.mongodb.client.model.geojson.Position;

import br.com.apiposto.codec.PostoCodec;
import br.com.apiposto.modelo.Posto;

public class PostoRepository {

	private MongoClient cliente;
	private MongoDatabase bancaDeDados;

	private void criarConexao() {
		Codec<Document> codec = MongoClient.getDefaultCodecRegistry().get(Document.class);

		PostoCodec postoCodec = new PostoCodec(codec);

		CodecRegistry registro = CodecRegistries.fromRegistries(MongoClient.getDefaultCodecRegistry(),
				CodecRegistries.fromCodecs(postoCodec));

		MongoClientOptions opcoes = MongoClientOptions.builder().codecRegistry(registro).build();

		this.cliente = new MongoClient("localhost:27017", opcoes);
		this.bancaDeDados = cliente.getDatabase("test");

	}

	public void salvar(Posto posto) {

		criarConexao();
		MongoCollection<Posto> postos = this.bancaDeDados.getCollection("postos", Posto.class);
		if (posto.getId() == null) {
			postos.insertOne(posto);
		} else {
			postos.updateOne(Filters.eq("_id", posto.getId()), new Document("$set", posto));
		}

		fecharConexao();
	}

	private void fecharConexao() {
		this.cliente.close();
	}

	public List<Posto> obterTodosPostos() {
		criarConexao();
		MongoCollection<Posto> postos = this.bancaDeDados.getCollection("postos", Posto.class);

		MongoCursor<Posto> resultados = postos.find().iterator();

		List<Posto> postosEncontrados = popularPostos(resultados);
		fecharConexao();

		return postosEncontrados;

	}

	private List<Posto> popularPostos(MongoCursor<Posto> resultados) {
		List<Posto> postos = new ArrayList<>();
		while (resultados.hasNext()) {
			postos.add(resultados.next());
		}
		return postos;
	}

	public List<Posto> pesquisaPorGeolocalizacao(Posto posto) {
		criarConexao();
		MongoCollection<Posto> postoCollection = this.bancaDeDados.getCollection("postos", Posto.class);

		postoCollection.createIndex(Indexes.geo2dsphere("ubicacion"));

		List<Double> coordinates = posto.getUbicacion().getCoordenadas();
		Point pontoReferencia = new Point(new Position(coordinates.get(0), coordinates.get(1)));

		MongoCursor<Posto> resultados = postoCollection
				.find(Filters.nearSphere("ubicacion", pontoReferencia, 2000.0, 0.0)).limit(2).skip(1).iterator();

		List<Posto> postos = popularPostos(resultados);

		fecharConexao();
		return postos;
	}

	public Posto obterPostoPor(String id) {
		criarConexao();
		MongoCollection<Posto> postos = this.bancaDeDados.getCollection("postos", Posto.class);
		Posto posto = postos.find(Filters.eq("_id", new ObjectId(id))).first();
		return posto;
	}

	public List<Posto> pesquisarPor(String nome) {
		criarConexao();
		MongoCollection<Posto> postoCollection = this.bancaDeDados.getCollection("postos", Posto.class);
		MongoCursor<Posto> resultados = postoCollection.find(Filters.eq("nome", nome), Posto.class).iterator();
		List<Posto> postos = popularPostos(resultados);

		fecharConexao();

		return postos;
	}

}
