![api_diagrampng](https://github.com/artwwl/pokemon-api-service/assets/100335019/3152cc49-0359-4a7c-acda-70aca275df27)

This is my first API build and my first ever contact with Kotlin.<br>
I chose Kotlin over Java (which is a language I had some contact with in the past in college freshman year) to challenge myself and<br>
to show that I'm completely capable of figuring a new technology out pretty quickly.<br>
<br>
DOCUMENTATION<br>
The API runs on port 8080, so, to make a request, use localhost:8080.<br>
<br>
"/pokemons" fetches all pokemons from the PokeAPI database.<br>
"/pokemons?query={YOUR_QUERY}" fetches all pokemons that include a substring that matches your query.<br>
"/pokemons?sort={SORT_BY}" fetches all pokemons sorted by alphabetical order ("alphabetical") or word length ("length"),<br> 
defaults to alphabetical.<br>
<br>
"/pokemons/highlight" fetches all pokemons but adds an extra key ("highlight") to each of them, which has a value of the pokemon name with <br>
"\<pre>\</pre>" tags containing the substring of the name that matched the query provided by the user making the request. If no query is<br> provided,
it highlights the entire name.<br>
Also accepts both "sort" and "query" parameters.
