# adventure-legacy
Text-based Adventure RPG, with navigation, encounters, a boss fight, and working save system.

This is a text-based adventure game that I coded back in high school. It features 5 levels, with an interactive text map, working battle encounters, and a save system. The only change I've made from the original is some debug code that makes your character super-overpowered, to make testing this game easier. Asides from that, the code was taken from is what was probably the final iteration of this game.

There is quite a bit of random "LULZ" humor, as I wrote it back in high school, and that was my style of humor at the time. The code also isn't as organized as I'd like it to be nowadays, and I didn't have as much experience as I have now in programming. I may revisit this project in the future if I become interested in doing so. However, I will keep this intact for historical purposes.

This game has some bugs I've noticed while quickly testing. When you have enough exp to be level 20+, you'll get a level-up warning message everytime you gain exp. This probably needs to be revised to only display this once, or to remove this entirely. Also, sometimes, when getting close to the level complete tile, I noticed that it wouldn't appear sometimes on the map. Not sure why this is, either way, I can still complete the level, just thought it was weird.

The game itself is a text-based adventure, where you type in commands to do things in the game. For example, in the map, you can type in "right" to move your character 1 tile to the right and "left" to move your character 1 tile to the left. After beginning in a game, you will start on the level 1 map. The map will looks as follows:

PooooooooE

You are P, o are walakble tiles, and E is the end of level marker. You will also encounter x tiles, which are obstacles you cannot walk on. You goal is to navigate to the end-of-level marker at the end of each level. This will not be easy, as you may randomly encounter monsters while walking, that you will have to fight. These encounters get increasingly stronger each level, so make sure you are prepared before going to the next level.

In encounters, you can attack, heal, and examine the enemy. You will take turns attacking the monster, until one of you is the victor. Each encounter you win will give you exp, depending on what monster you killed, and possibly a potion to heal you during battle. GAin enough exp, and you can gain levels to increase your stats. Non-members can level up to 10, while memebers can level up to 20.

At the fifth level, there will be a boss. Kill it to win the game.

There is also mention of membership. Apparently, it caps your level to 10, and is honestly just a stupid inside joke (the membership codes exist in the source somewhere, feel free to give yourself membership). It might some other things, but I haven't expolred it all that much.
