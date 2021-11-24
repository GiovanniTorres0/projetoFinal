package br.com.apiposto.codec;

import java.util.ArrayList;
import java.util.List;

import org.bson.BsonReader;
import org.bson.BsonString;
import org.bson.BsonValue;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.Codec;
import org.bson.codecs.CollectibleCodec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.types.ObjectId;

import br.com.apiposto.modelo.Ubicacion;
import br.com.apiposto.modelo.Usuario;

public class UsuarioCodec implements CollectibleCodec<Usuario> {

	private Codec<Document> codec;

	public UsuarioCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Usuario usuario, EncoderContext encoder) {
		ObjectId id = usuario.getId();
		String nome = usuario.getNome();
		Ubicacion ubicacion = usuario.getUbicacion();
		Document document = new Document();

		document.put("_id", id);
		document.put("nome", nome);

		List<Double> coordinates = new ArrayList<Double>();
		for (Double location : ubicacion.getCoordenadas()) {

			coordinates.add(location);
		}

		document.put("ubicacion", new Document().append("endereco", ubicacion.getEndereco())
				.append("coordinates", coordinates).append("type", ubicacion.getType()));

		codec.encode(writer, document, encoder);

	}

	@Override
	public Usuario decode(BsonReader reader, DecoderContext decoder) {
		Document document = codec.decode(reader, decoder);

		Usuario usuario = new Usuario();

		usuario.setId(document.getObjectId("_id"));
		usuario.setNome(document.getString("nome"));

		return usuario;

	}

	public boolean documentHasId(Usuario usuario) {
		return usuario.getId() == null;
	}

	public Usuario generateIdIfAbsentFromDocument(Usuario usuario) {
		return documentHasId(usuario) ? usuario.criarId() : usuario;
	}

	public BsonValue getDocumentId(Usuario usuario) {
		if (!documentHasId(usuario)) {
			throw new IllegalStateException("Esse Document nao tem id");
		}

		return new BsonString(usuario.getId().toHexString());

	}

	@Override
	public Class<Usuario> getEncoderClass() {
		return Usuario.class;
	}

}
