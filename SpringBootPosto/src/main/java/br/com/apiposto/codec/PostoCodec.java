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

import br.com.apiposto.modelo.Posto;
import br.com.apiposto.modelo.Ubicacion;

public class PostoCodec implements CollectibleCodec<Posto> {

	private Codec<Document> codec;

	public PostoCodec(Codec<Document> codec) {
		this.codec = codec;
	}

	@Override
	public void encode(BsonWriter writer, Posto posto, EncoderContext encoder) {
		ObjectId id = posto.getId();
		String nome = posto.getNome();
		Ubicacion ubicacion = posto.getUbicacion();
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
	public Posto decode(BsonReader reader, DecoderContext decoder) {
		Document document = codec.decode(reader, decoder);

		Posto posto = new Posto();

		posto.setId(document.getObjectId("_id"));
		posto.setNome(document.getString("nome"));

		return posto;

	}

	public boolean documentHasId(Posto posto) {
		return posto.getId() == null;
	}

	public Posto generateIdIfAbsentFromDocument(Posto posto) {
		return documentHasId(posto) ? posto.criarId() : posto;
	}

	public BsonValue getDocumentId(Posto posto) {
		if (!documentHasId(posto)) {
			throw new IllegalStateException("Esse Document nao tem id");
		}

		return new BsonString(posto.getId().toHexString());

	}

	@Override
	public Class<Posto> getEncoderClass() {
		return Posto.class;
	}

}
