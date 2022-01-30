package com.arthursales.smogon

import com.arthursales.smogon.models.SmogonResponse
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.junit.Assert.assertEquals
import org.junit.Test


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testJsonObjectToJavaObject(){

        val objectMapper = ObjectMapper()
        val json = "{\n" +
                "    \"al\" : { \"type\": \"admin\", \"prefs\" : { \"arbitrary_key_a\":\"arbitary_value_a\", \"arbitrary_key_b\":\"arbitary_value_b\"}},\n" +
                "    \"bert\" : {\"type\": \"user\", \"prefs\" : { \"arbitrary_key_x\":\"arbitary_value_x\", \"arbitrary_key_y\":\"arbitary_value_y\"}}\n" +
                "}"

//        val product: Product = objectMapper.convertValue(json, Product::class.java)
        val result: Map<String, Product> = Gson().fromJson<Map<String, Product>>(
            json,
            object : TypeToken<Map<String?, Product?>?>() {}.type
        )


        assertEquals(result.get("name"), "Pear yPhone 72")
    }

    @Test
    fun testMainJackson(){
        val json =
            "{\n" +
                    "    \"name\": \"Pear yPhone 72\",\n" +
                    "    \"category\": \"cellphone\",\n" +
                    "    \"details\": {\n" +
                    "        \"displayAspectRatio\": \"97:3\",\n" +
                    "        \"audioConnector\": \"none\"\n" +
                    "    }\n" +
                    "}\n"
        val mapper = ObjectMapper()
        val product: Product = mapper.readValue(json, Product::class.java)


        System.out.println(product.name)
        System.out.println(product.details)

        val list: List<String> = ArrayList<String>(product.details.values)
        System.out.println(list)


        assertEquals(product.name, "Pear yPhone 72")
        assertEquals(product.details.get("audioConnector"), "none")
    }

    @Test
    fun testMainJacksonwithListOfObject(){
        val json =
            "        {\n" +
                    "            \"name\": \"Pear yPhone 72\",\n" +
                    "            \"category\": \"cellphone\",\n" +
                    "            \"details\": {\n" +
                    "                \"alakazam\": {\n" +
                    "                    \"id_\": 2453278,\n" +
                    "                    \"rank\": 6,\n" +
                    "                    \"pokemon\": \"alakazam\",\n" +
                    "                    \"usage_pct\": 43.20675,\n" +
                    "                    \"raw_usage\": 15363,\n" +
                    "                    \"dex\": 65,\n" +
                    "                    \"date\": \"2021-02\",\n" +
                    "                    \"tier\": \"gen1ou\"\n" +
                    "                },\n" +
                    "                \"tauros\": {\n" +
                    "                    \"id_\": 2453273,\n" +
                    "                    \"rank\": 1,\n" +
                    "                    \"pokemon\": \"tauros\",\n" +
                    "                    \"usage_pct\": 92.23503,\n" +
                    "                    \"dex\": 128,\n" +
                    "                    \"date\": \"2021-02\",\n" +
                    "                    \"tier\": \"gen1ou\"\n" +
                    "                }\n" +
                    "            }\n" +
                    "        }"
        val objectMapper = ObjectMapper()
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)

        val product: Pokemon = objectMapper.readValue(json, Pokemon::class.java)


        System.out.println(product.name)
        System.out.println(product.details)

        val list: List<SmogonResponse> = ArrayList<SmogonResponse>(product.details.values)
        System.out.println(list)


        assertEquals(product.name, "Pear yPhone 72")
    }
}

internal class Product {
    // common fields
    var name: String? = null
    var category: String? = null
    lateinit var details: HashMap<String, String> // standard getters and setters
}

internal class Pokemon {
    // common fields
    var name: String? = null
    var category: String? = null
    lateinit var details: HashMap<String, SmogonResponse> // standard getters and setters
}
