# Smart Hardware Shop

## Pre-requisites

| Library | Version                      |
|---------|------------------------------|
| JDK     | `Temurin-17.0.2+8`  (_v 17_) |

## Libraries & Frameworks

| Library     | Version  |
|-------------|----------|
| Angular     | `13.3.0` |
| Material UI | `13.3.5` |
| SpringBoot  | `2.6.7`  |

## Compiling the application

`mvn clean install`

> __Note__: This packages Angular for the `production` configuration. So the API will target: `http://prod.local:8080/`
>
> For accessing properly will need to modify your OS `virtual hosts` file, adding this line:
> 
> `127.0.0.1   prod.local`

| OS      | File                                    |
|---------|-----------------------------------------|
| Linux   | `/etc/hosts`                            |
| Mac     | `/etc/hosts`                            |
| Windows | `c:\windows\system32\drivers\etc\hosts` |

## Running the application

`java -jar backend/target/backend-0.0.1-SNAPSHOT.jar`

### Open browser in: `http://prod.local:8080`

> __Note:__ Both: Angular App and the Rest API runs under port `8080`

### Important

There are two branches: `master` and `api-http-entities`.
> `master` branch is compatible with the Angular app version
>
> `api-http-entities` is meant for Rest API best practices __ONLY__
>
>  - HTTP Status Codes
>  - Response Entity