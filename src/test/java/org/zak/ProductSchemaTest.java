package org.zak;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.reinert.jjschema.JsonSchemaGenerator;
import com.github.reinert.jjschema.SchemaGeneratorBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.zak.jjschema.Product;

import java.io.File;
import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static uk.co.datumedge.hamcrest.json.SameJSONAs.sameJSONAs;

public class ProductSchemaTest {

    @Test
    public void shouldSuccessfullyCreateJsonSchemaFromPojo() throws IOException {
        JsonNode expectedProductSchema  = createExpectedSchema();
        JsonNode actualProductSchema    = createActualSchema();

        assertThat(expectedProductSchema.toString(), sameJSONAs(actualProductSchema.toString()));
    }

    private JsonNode createActualSchema() {
        JsonSchemaGenerator v4generator = SchemaGeneratorBuilder.draftV4Schema().build();
        return v4generator.generateSchema(Product.class);
    }

    private JsonNode createExpectedSchema() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        File schemaFile           = FileUtils.toFile(getClass().getResource("/product-schema.json"));

        return objectMapper.readTree(FileUtils.readFileToString(schemaFile));
    }
}
