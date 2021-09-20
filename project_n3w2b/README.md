# An Interactive Modified Pokedex

## Why a Pokedex?

The idea stems from my love for Pokemon. While it is not something that would be considered useful by most people, 
those with a passion for Pokemon may find many uses for it. For example, having a Pokedex open while playing a Pokemon
game can be very useful. While the Pokemon games do have a built-in Pokedex, it is not exactly user-friendly. There is
no built-in **search function** that lets you look up a Pokemon you want. You'd instead have to scroll through the whole
application looking for your specific Pokemon. This is especially cumbersome for more invested players who do shiny
hunts, or specific passive ability hunts. By having a Pokedex open infront which displays information such as where a
specific Pokemon can be found, and such, will make the task of information searching much less cumbersome for
players.

Hence, the following application will present players with a database of Pokemon (will not include every Pokemon till 
now as that will increase the time to build a database of Pokemon by a lot). Each Pokemon will have an image and basic
information about its species listed in its tab that the player can use to find specific information that they may
require at the time.

## User Stories

- I want to be able to go through the list of Pokemon available.

- I want to be able to add to an existing list of Pokemon.

- I want to be able to search for a specific Pokemon using a search bar.

- I want to be able to select a specific Pokemon and view detailed information about it.

- I want to be able to move to the next Pokemon tab without going back to the list of all Pokemons.

- I want to be able to save the Pokemon I add before I quit the Pokedex.

- I want to be able to load the Pokemon I saved from my previous session when I launch the Pokedex.

## Phase 4: Task 2

- Made Pokedex class robust by checking and testing for exceptions on getPokemonByIndex().

## Phase 4: Task 3

- Refactor methods from GUI class into separate classes that each deal with a component. For example have a button
class that deals with just JButtons.

- Refactor methods from the PokedexApp class that deal with similar commands. For example, 
processPokemonSelectionCommand() and moveToNextPokemon(Pokemon current) deal with viewing a list of Pokemon and moving
along the list. They could've been a different class.

