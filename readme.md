# Springboot Custom Interceptor

<div align="center" style="text-align:center">
	<a href="https://github.com"><img src="https://img.shields.io/badge/GitHub-100000?style=for-the-badge&logo=github&logoColor=white"/></a>
	<a href="https://spring.io"><img src="https://img.shields.io/badge/SpringBoot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white"/></a>
	<a href="https://maven.apache.org"><img src="https://img.shields.io/badge/Maven-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white"/></a>
	<a href="https://www.jetbrains.com/idea"><img src="https://img.shields.io/badge/IntelliJ_IDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white"/></a>
	<a href="https://junit.org/junit5"><img src="https://img.shields.io/badge/JUnit-25A162?style=for-the-badge&logo=junit5&logoColor=white"/></a>
</div>

## Description

Custom interceptor to obtain a detailed log of the application's requests and responses and simple error handling.

## Structure of project

```
.
├── dto                    	# DTO example (Request and Response)
├── exception			# Exception Handler
│   ├── controller          	# Rest Advice Controller
│   ├── dto         		# DTO response for exception handling
├── interceptor         	# Interceptor config
│   ├── dto         		# Logger DTO
│   ├── service         	# Service to log trace
├── web				# Rest Controller
```

## Parameters to trace

* **method** : { GET, POST, PUT, PATCH, OPTIONS, DELETE }
* **ip** : Request Ip Adrress
* **path** : Request URI
* **parametersIn** : Parameters of request (@RequestBody or @RequestParam)
* **parametersOut** : Body of response
* **codeResponse** : HTTP status
* **timeResponse** : Response time of service

## Defaults values

* URL Endpoint: [http://127.0.0.1:8010/interceptor/v1/test](http://127.0.0.1:8010/interceptor/v1/test)
* Port: 8010
* Context path: /interceptor/v1

## How to use

1. Clone repository.
2. Open folder.
3. Run:
	```
	mvn clean && mvn install
	```
4. Open in your favorite IDE (I use Intellij Idea).
5. Use postman or SoapUI to test.

## Outputs and screenshots

An example of output.
```
LOG{
	"method":"DELETE",
	"ip":"127.0.0.1",
	"path":"/interceptor/v1/test",
	"parametersIn":{},
	"parametersOut":
		{
			"code":"400","message":"Missing parameter: id"
		},
	"codeResponse":400,
	"timeResponseService":"69 ms"
}
```

## Do you want to support me?
<br>
<a href="https://www.buymeacoffee.com/JoanSalomon" target="_blank"><img src="https://cdn.buymeacoffee.com/buttons/v2/default-red.png" alt="Buy Me A Coffee" style="height: 60px !important;width: 217px !important;" ></a>
