-- HTTP POST script for micronaut.rest.example

wrk.method = "POST"
wrk.body   = "{ \"userId\":\"01FM4JE6DVQ9MP08YREJRW6AYZ\", \"amount\":10, \"accountFrom\":\"12345678\", \"accountTo\":\"12345679\" }"
wrk.headers["Content-Type"] = "application/json"

