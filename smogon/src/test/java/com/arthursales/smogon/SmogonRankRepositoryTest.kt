package com.arthursales.smogon

import com.arthursales.smogon.models.SmogonResponse
import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import org.junit.Assert
import org.junit.Test

class SmogonRankRepositoryTest {

    @Test
    fun testJsonElement{
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

    }
    
    @Test
    fun testgetSmogonRankData(){
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


        Assert.assertEquals(product.name, "Pear yPhone 72")
    }
}
