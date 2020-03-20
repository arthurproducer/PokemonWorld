# PokemonWorld
Trabalhando com a API de Pokemon, usando MVVM, Retrofit e androidX.

## Nossa Missão

App desenvolvido para visualizar os nossos amados pokémons. 

## Nossa Proposta

O app possui uma tela de login focada no atendimento dos usuários da reqres.in,
que poderam cadastrar uma senha para o seu email já existente e assim poder visualizar todos os Pokemon e selecionar seus favoritos. 

## Como funciona
O usuário baixa o aplicativo, tenta logar com seu e-mail, se o mesmo já estiver cadastrado é verificado se existe uma senha associada aquele email, caso não exista ele será direcionado para o cadastro da mesma, uma vez cadastrada ele terá acesso ao app.
Dentro do aplicativo será exibido uma lista de pokemons dos quais o usuário poderá selecionar os seus favoritos, que ficaram visiveis mesmo offline (no caso do usuário permanecer logado após utilizar o app.)

## Características Técnicas

Aplicativo desenvolvido em Kotlin, utilizando androidX,SQLite banco de dados do android que permite salvar dados no próprio device,
Koin para injeção de dependência, Retrofit para o webservice, Gson para parse do json, Stetho para debug das requests, Picasso para tratamento de imagem, RecyclerView para tratamento de listas, AAC(Android Architecture Components) para o uso de LiveData necessárias na arquitetura MVVM, por fim o Material Design do Google para uso de BottomBar,Toolbar e outros apetrechos para design.  

###### Kotlin; SQLite; MVVM; Retrofit; Koin;

## Instruções para uso

As instruções abaixo permitirão baixar uma cópia do projeto, para ser executada em sua estação, para validação das funcionalidades.

### Pré-requisitos

Clone este repositório em sua estação:

```
git clone https://github.com/arthurproducer/PokemonWorld/tree/login/with/SampleAPI
```

Accesse o directório do projeto:

```
cd pokemonworld
```

## Detalhando as telas
| ![Page1](printscreen/Splash.png)  | ![Page2](printscreen/Login.png) | ![Page3](printscreen/Main.png) |
|:---:|:---:|:---:|
| Splash | Login | Menu Principal |

### Splash
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/Splash.png" height="160" width="100">

### Login
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/Login.png" height="160" width="100">

### Main
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/Main.png" height="160" width="100">

### Listas de Pokemon
| ![Page1](printscreen/PokemonList.png)  | ![Page2](printscreen/PokemonDetails.png) | ![Page3](printscreen/PokemonDetailsBtFavorite.png) |
|:---:|:---:|:---:|
| Lista de Personagens | Favoritos | Detalhes do Personagem |
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/PokemonList.png" height="160" width="100">

### Detalhes do Pokemon
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/PokemonDetails.png" height="160" width="100">
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/PokemonDetailsBtFavorite.png" height="160" width="100">

### Pokemon Favoritos
<img src="https://github.com/arthurproducer/PokemonWorld/blob/login/with/SampleAPI/printscreen/PokemonFavorites.png" height="160" width="100">


## Version

1.0.0


## Authors
* **Arthur Sales** - [arthurproducer](https://github.com/arthurproducer)
