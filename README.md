# Santorini

## Starting a game

1. Start the server:
- `cd Santorini`
- `mvn install`
- Using Intellij, open the `Santorini` folder. Go to this file `src/main/java/edu/cmu/cs214/hw3/server/App.java` and click the right triangle in the top-right to run the server. 

3. Start the frontend:
- Go back to the root of this repo (the same level with `Santorini`). 
- Install frontend Dependencies: `cd frontend` && `npm install`
- Build: `npm run build`
- Start the frontend `npm start`
- Go to `localhost:3000`
- Choose god cards for Player1 and Player2 and click `Start Game` at the bottom. You will be redirected into board page.  
If you don't click on the god cards, then there is no god card chosen. 

**Note: Note that please first start the server and run the game.   
Always start a new game in the `localhost:3000` if you restart the server (meaning if you restart the server, don't refresh `/board`, click `New Game` or go to `localhost:3000` )

## God Cards
- Demeter
- Pan
- Minotaur
- Apollo
- Hephaestus
- Artemis
- Atlas


## References
- Images:
  - Board background: https://www.tripadvisor.com/Tourism-g189433-Santorini_Cyclades_South_Aegean-Vacations.html
  - God cards images are from https://www.ultraboardgames.com/santorini/game-rules.php

- React official tutorial (tictactoe) https://reactjs.org/tutorial/tutorial.html
- Recitation code: https://github.com/CMU-17-214/s22-rec07-solution
- Frontend React UI library: https://ant.design/