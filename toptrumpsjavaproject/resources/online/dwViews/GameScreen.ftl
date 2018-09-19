<html>
	<head>
		<!-- Web page title -->
    	<title>Top Trumps</title>
    	
    	<!-- Import JQuery, as it provides functions you will probably find useful (see https://jquery.com/) -->
    	<script src="https://code.jquery.com/jquery-2.1.1.js"></script>
    	<script src="https://code.jquery.com/ui/1.11.1/jquery-ui.js"></script>
    	<link rel="stylesheet" href="https://code.jquery.com/ui/1.11.1/themes/flick/jquery-ui.css">

		<!-- Optional Styling of the Website, for the demo I used Bootstrap (see https://getbootstrap.com/docs/4.0/getting-started/introduction/) -->
		<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/TREC_IS/bootstrap.min.css">
    	<script src="http://dcs.gla.ac.uk/~richardm/vex.combined.min.js"></script>
    	<script>vex.defaultOptions.className = 'vex-theme-os';</script>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex.css"/>
    	<link rel="stylesheet" href="http://dcs.gla.ac.uk/~richardm/assets/stylesheets/vex-theme-os.css"/>
    	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css">

	</head>
    <body onload="initalize()"> <!-- Call the initalize method when the page loads -->

    <!-- CSS of the GameScreen -->
    <style>

        body {
            background: #b5b5b5;
            color: black;
            max-width: 3072px;
            position: relative;
            margin: 0;
            padding-bottom: 6rem;
            min-height: 100%;
        }
        .navbar {
            background-color: #434343;
            color: #d6b945;
            text-align: center
        }

        a.brand-name {
            font-size: 2.125rem;
            font-family: 'Arial Rounded MT Bold', Arial, sans-serif;
        }

        p {
            margin-bottom: 9px;
        }

        h4 {
            text-align: center;
        }

        .col {
            margin-top: 7%;
        }
        .col-6 {
            margin-top: 15%;
            padding-left: 5%;
            padding-right: 5%;
        }

        .card-img-top {
            width: 253px;
            height: 130px;
        }

        #playersTurn {
            text-align: center;

        }

        .card {
            margin-top: 55px;
            border: solid black 1px;
        }

        .card-body {
            border-bottom: solid black 1px;
        }

        .list-group-item.active {
            background-color: #edc115

        }

        .list-group-item {
            padding-bottom: 11px;
        }

        .animateButton {
            width: 300px;
            height: 50px;
            border: solid black 1px;
            background-color: #d6b945;
            margin-top: 20%;
            margin-left: auto;
            padding-top: 3%;
        }

        .animateButton: hover {
            background-color: slategrey;
        }

        .buttonLoc {
            position: absolute;
            left: 50%;
            top: 70%;
            transform: translate(-50%, -70%);
        }

        .btn-group-vertical {
            width: 50%;
        }

        .catButton {
            margin-left: auto;
        }

        .catBtn {
            padding-top: 6%;
        }


        .playersCardsLeft {
            margin-top: 60px;
        }

        #round {
            text-align: center;
        }

        strong {
            margin-left: 10px;
        }
        
        .round{
            windows: 100%;
            text-align: center;
            
        }
        #draw {
            text-align: center;
        }

        #nextRound {
            text-align: center;
        }

        .result {
            width: 60%;
            height: 8%;
            margin-left: auto;
            margin-right: auto;
            text-align: center;
        }

        #resultOfPlayers {
            margin-top: 8%;
        }

        a.animateAnotherButton:link, a.animateAnotherButton:visited {
            position: relative;
            display: block;
            margin: 30px auto 0;
            padding: 14px 15px;
            color: black;
            font-size:14px;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            text-transform: uppercase;
            overflow: hidden;
            letter-spacing: .08em;
            border-radius: 0;
            text-shadow: 0 0 1px rgba(0, 0, 0, 0.2), 0 1px 0 rgba(0, 0, 0, 0.2);
            -webkit-transition: all 1s ease;
            -moz-transition: all 1s ease;
            -o-transition: all 1s ease;
            transition: all 1s ease;
        }
        a.animateAnotherButton:link:after, a.animateAnotherButton:visited:after {
            content: "";
            position: absolute;
            height: 0%;
            left: 50%;
            top: 50%;
            width: 150%;
            z-index: -1;
            -webkit-transition: all 0.75s ease 0s;
            -moz-transition: all 0.75s ease 0s;
            -o-transition: all 0.75s ease 0s;
            transition: all 0.75s ease 0s;
        }
        a.animateAnotherButton:link:hover, a.animateAnotherButton:visited:hover {
            color: black;
            text-shadow: none;
        }
        a.animateAnotherButton:link:hover:after, a.animateAnotherButton:visited:hover:after {
            height: 450%;
        }
        a.animateAnotherButton:link, a.animateAnotherButton:visited {
            position: relative;
            display: block;
            margin: 30px auto 0;
            padding: 14px 15px;
            color: black;
            font-size:14px;
            border-radius: 0;
            font-weight: bold;
            text-align: center;
            text-decoration: none;
            text-transform: uppercase;
            overflow: hidden;
            letter-spacing: .08em;
            text-shadow: 0 0 1px rgba(0, 0, 0, 0.2), 0 1px 0 rgba(0, 0, 0, 0.2);
            -webkit-transition: all 1s ease;
            -moz-transition: all 1s ease;
            -o-transition: all 1s ease;
            transition: all 1s ease;
        }

        /*
        Play Game button animation
         */
        a.animateAnotherButton.playButton {
            border: 2px solid #000000;
        }
        a.animateAnotherButton.playButton:after {
            background: #d6b945;
            -moz-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
            -ms-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
            -webkit-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
            transform: translateX(-50%) translateY(-50%) rotate(-25deg);
        }

        /*
        Statistics button animation
         */
        a.animateAnotherButton.statsButton {
            border: 2px solid #000000;
        }
        a.animateAnotherButton.statsButton:after {
            background: #d6b945;
            -moz-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            -ms-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            -webkit-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            transform: translateX(-50%) translateY(-50%) rotate(25deg);
        }

        .centreEverything{
            position: absolute;
            top:50%;
            left:50%;
            transform: translate(-50%, -50%);
        }


        .centerButtons{
            margin:0 auto;
            float:left;
        }

        /*
        .gameEnded {
            width: available;
            align-content: center;
        }
        */

        .footer {
            position: absolute;
            right: 0;
            bottom: 0;
            left: 0;
            padding: 1rem;
            width: 100%;
            background-color: #434343;
            color: #d6b945;
            text-align: center;
        }

    </style>

    <!-- HTML part of the GameScreen -->

    <!-- Navigation bar and game image -->
    <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
        <a class="navbar-brand" href="http://localhost:7777/toptrumps">
            <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
        </a>
        <a class="brand-name navbar-left">The world's best card game!</a>
    </nav>

    <!--LEFT SIDE OF SCREEN -->
    <div class="container">
        <div class="row">
            <div class="col">
                <div class="currentCard">
                    <h3>Your current card</h3>
                </div>

                <!-- playersCard object-->
                <div class="card">
                    <img class="card-img-top" src="http://www.twizzle.co.uk/wp-content/uploads/2016/11/mfalcon.jpg" alt="Card image cap">
                    <div class="card-body">
                        <h5 class="cardDescription"></h5>
                    </div>
                    <ul class="list-group list-group-flush">
                        <li class="list-group-item">
                            <h6 id="nameOfCat1"></h6>
                            <h6 id="cat1Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat2"></h6>
                            <h6 id="cat2Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat3"></h6>
                            <h6 id="cat3Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat4"></h6>
                            <h6 id="cat4Value"></h6>
                        </li>
                        <li class="list-group-item">
                            <h6 id="nameOfCat5"></h6>
                            <h6 id="cat5Value"></h6>
                        </li>
                    </ul>
                </div>
            </div>


            <!--MIDDLE OF SCREEN (changes during the game) -->
            <!-- choose category form when players turn -->
            <div class="col-6 middle">

                <!-- first round -->
                <div align="center" id="firstRound">
                    <h4>Start the first round</h4>
                    <div class="btn animateButton btn-primary buttonLoc" onclick="activePlayer()"><p id="nextRound">First Round</p></div>
                </div>


                <!-- players turn -->
                  <div id="playersTurn">
                       <h2>It's your turn!</h2>
                      <h4>Choose a category</h4>
                      <!-- list where user can choose a category -->
                      <div class="btn-group-vertical" role="group">
                          <div class="btn animateButton btn-primary catButton"> <p class="catBtn" id="nameOfCat1Btn" onclick="humanPlayerChosenCategory($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat2Btn" onclick="humanPlayerChosenCategory($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat3Btn" onclick="humanPlayerChosenCategory($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat4Btn" onclick="humanPlayerChosenCategory($(this).text())"></p></div>
                          <div class="btn animateButton btn-primary"> <p class="catBtn" id="nameOfCat5Btn" onclick="humanPlayerChosenCategory($(this).text())"></p></div>
                      </div>
                  </div>

                <!-- chosen category -->
                   <div align="center" id="chosenCat">
                       <h4>Chosen category: <strong id="chosenCategory"></strong></h4>
                       <div class="btn animateButton btn-primary  buttonLoc" onclick="roundResult()"><p id="playGameButton">Show result</p></div>
                   </div>

                <!-- round result -->
                <div id="round">
                       <h4>Round winner:<strong id="roundWinner"></strong></h4>

                   <div id="resultOfPlayers" >
                       <!-- list of all players in the game and their current number of cards -->
                   <ul class="list-group list-group-flush">
                           <li class="list-group-item result"><p  class="nameOfPlayer1"></p><p id="valueCatPlayer1"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer2"></p><p id="valueCatPlayer2"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer3"></p><p id="valueCatPlayer3"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer4"></p><p id="valueCatPlayer4"></p></li>
                           <li class="list-group-item result"><p  class="nameOfPlayer5"></p><p id="valueCatPlayer5"></p></li>
                         </ul>
                       <div class="btn animateButton btn-primary buttonLoc" onclick="activePlayer()"> <p>Next Round</p></div>
                       </div>
                   </div>

                <!-- draw occurred -->
                    <div align="center" id="draw">
                         <h4>There was a draw!</h4>
                         <div class="btn animateButton btn-primary buttonLoc" onclick="activePlayer()"><p>Next Round</p></div>
                    </div>

            </div>

            <!-- RIGHT SIDE OF THE SCREEN -->
            <div class="col">
                <div class="updatedGameData">
                    <h6>Number of cards in communal pile:</h6>
                    <h6 id="numberOfCardsInCommunalPile"></h6>
                </div>

                <div class="playersCardsLeft">
                    <ul class="list-group">
                        <li class="list-group-item">
                            <p class="nameOfPlayer1"></p>
                            <p id="cardsOfPlayer1"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer2"></p>
                            <p id="cardsOfPlayer2"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer3"></p>
                            <p id="cardsOfPlayer3"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer4"></p>
                            <p id="cardsOfPlayer4"></p>
                        </li>
                        <li class="list-group-item">
                            <p class="nameOfPlayer5"></p>
                            <p id="cardsOfPlayer5"></p>
                        </li>
                    </ul>
                </div>

            </div>
        </div>

    </div>
    </div>

    <!-- Show the number of rounds -->
    <div class="updatedGameData round">
        <h6>Number of rounds:</h6>
        <h6 id="numberOfRounds"></h6>
    </div>

    <!-- game ended prompt -->

    <div class="gameEnded container body centreEverything">
        <div class="col-md-12 text-center">
            <h1>GAME OVER</h1>
            <br>
            <h3>The winner is:</h3>
            <br>
            <h3 id="gameWinner"></h3>
        </div>

        <div class = "col-md-6 centerButtons">
            <a href="http://localhost:7777/toptrumps/game" onclick="startGame()"  class="btn animateAnotherButton statsButton">Play Game</a>
        </div>

        <div class = "col-md-6 centerButtons">
            <a href="http://localhost:7777/toptrumps/stats" class="btn animateAnotherButton playButton">Show Statistics</a>
        </div>

    </div>

    <!-- Footer of the page -->
    <div class="footer">powered by THE GOAT GAMERS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>

    <!-- JavaScript part of the page -->
		<script type="text/javascript">

            var activePlayerVar;
            var numOfPlayers;

            /**
             * method called on page load
             * */
			function initalize() {
                startSetup(); // update user interface
			}


            /**
             * start the game data request
             * */
            function startGame() {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/startGame"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                };
                xhr.send();
            }

            // FUNCTIONALITY TO CALL REST API METHODS

            /**
             * write to database
             * */
            function writeToDB() {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/writeToDB"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    console.log("WRITE RESULTS TO DATABASE");
                };
                xhr.send();
            }


            /**
             * get the current number of players in the game
             * */
            function numberOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    numOfPlayers = parseInt(responseText[0]);


                };
                xhr.send();
            }

            /**
             * get the number of rounds
             * */
            function roundCount() {
                // First create a CORS request, this is the message we are going to send (a get request in this case)
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/roundCount"); // Request type and URL
                // Message is not sent yet, but we can check that the browser supports CORS
                if (!xhr) {
                    alert("CORS not supported");
                }
                // CORS requests are Asynchronous, i.e. we do not wait for a response, instead we define an action
                // to do when the response arrives
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $("#numberOfRounds").text(parseInt(responseText[0]));
                };
                // We have done everything we need to prepare the CORS request, so send it
                xhr.send();
            }

            /**
             * get the current number of cards in the communal pile
             * */
            function getNumOfCardsInComPile() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/numCardsInComPile"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#numberOfCardsInCommunalPile').text(parseInt(responseText[0]));
                };
                xhr.send();
            }

            /**
             * get the names of all players in the game
             * */
            function namesOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/namesOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function() {
                   var  responseText = JSON.parse(xhr.response); // the text of the response
                    var n = parseInt(responseText[0]); //number of players
                    for (var i=1; i<(n+1); i++) {
                        $(".nameOfPlayer"+i).text(responseText[i]);
                    }
                };
                xhr.send();
            }

            /**
             * get the current active player
             * change user interface to show current active player
             * test if it's the player's turn and change user interface accordingly
             */
            function activePlayer() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/activePlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $("p").parent().removeClass("active");
                    console.log("response old active player: " + activePlayerVar);
                    activePlayerVar = responseText;
                    $("p:contains('"+ activePlayerVar +"')").parent().toggleClass("active");
                    console.log("response active player: " + activePlayerVar);

                    //check if the current active player is the user (Human Player)
                    if(activePlayerVar==="Human Player") {
                        showChooseCategory();
                    } else {
                        getAIchosenCategory();
                        showSelectedCategory();
                    }


                };
                xhr.send();
            }

            /**
             * get the category names of the cards in the deck
             */
            function cardCatNames() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/cardCatNames"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for(var i=0; i<responseText.length; i++) {
                        $("#nameOfCat"+(i+1)).text(responseText[i]);
                        $("#nameOfCat"+(i+1)+"Btn").text(responseText[i]);
                    }
                };
                xhr.send();
            }

            /**
             * get the first card description in the user's deck
             * get current card's picture
             */
            function getFirstCardDescription() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getFirstCardDescription"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $(".cardDescription").text(responseText);
                    $(".card-img-top").attr("src", "http://dcs.gla.ac.uk/~richardm/TopTrumps/"+responseText+".jpg")
                };
                xhr.send();
            }

            /**
             * get the values of each category of the first cards in the player's deck
             */
            function getFirstCardValues() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getFirstCardValues"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for (var i=0; i<responseText.length; i++) {
                        $("#cat"+(i+1)+"Value").text(parseInt(responseText[i]));
                    }
                };
                xhr.send();
            }

            /**
             * the winner of the current round
             * check if there was a draw and update user interface accordingly
             */
            function getRoundWinner() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getRoundWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#roundWinner').text(responseText);

                    //check if there was a draw
                     if ($("#roundWinner").text() === "none") {
                         roundCount();
                         playerNamesAndNumOfCards();
                        showDrawOccurred();
                    } else {
                         $("p:contains('"+ responseText +"')").parent().toggleClass("active");
                         $("p:contains('"+ activePlayerVar +"')").parent().toggleClass("active");
                        namesOfPlayers();
                        showRoundResult();
                        roundCount();
                        playerNamesAndNumOfCards();
                    }
                };
                xhr.send();
            }

            /**
             * end game without human player
             */
            function endGameWithoutHumanPlayer() {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/endGameWithoutHumanPlayer"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var  gameWinner = JSON.parse(xhr.response);
                    $(".row").hide(); //hide the rest of the user interface
                    $("#gameWinner").text(gameWinner); //change user interface accordingly
                    $(".gameEnded").show(); //show the winner in the user interface
                    writeToDB(); //write results to database

                };
                xhr.send();
            }

            /**
             * get the overall game winner
             */
            function getGameWinner() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getGameWinner"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                   var  gameWinner = JSON.parse(xhr.response);
                     console.log(gameWinner);
                    $(".row").hide(); //hide the rest of the user interface
                    $("#gameWinner").text(gameWinner); //change user interface accordingly
                    $(".gameEnded").show(); //show the winner in the user interface
                    writeToDB(); //write results to database
                };
                xhr.send();
            }

            /**
             * get the names of all players and their current number of cards in their decks
             * change user interface accordingly
             * check if the user (Human Player) is still in the game
             */
            function playerNamesAndNumOfCards() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/playerNamesAndNumOfCards"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var namesAndCards = JSON.parse(xhr.response);

					 if(numOfPlayers == 1) {
                        getGameWinner();
                    }
                    //empty fields in UI
                    for(var m=0; m<6; m++){
                        $(".nameOfPlayer" + (m+1)).text("");
                        $("#cardsOfPlayer" + (m+1)).text("");
                    }

                    console.log(namesAndCards);
                    var allPlayers = namesAndCards.length;
                    console.log(allPlayers);

                    //update user interface with names and current number of cards in the deck
                    var j=0;
                    for(var i=0; i<allPlayers; i++) {
                        $(".nameOfPlayer" + (j + 1)).text(namesAndCards[i]);
                        console.log(namesAndCards[i]);
                        $("#cardsOfPlayer" + (j + 1)).text(parseInt(namesAndCards[i + 1]));
                        console.log(namesAndCards[i + 1]);
                        j++;
                        i++;
                    }

                    //check if the user (human player) is still in the game
                    if(namesAndCards[0] !== "Human Player" && numOfPlayers>1) {
                            $(".row").hide();
                            endGameWithoutHumanPlayer();
                    }  else {
                        getFirstCardValues();
                        getFirstCardDescription();
                    }
                };
                xhr.send();
            }

            /**
             * get all values of all players in the currently chosen category
             * update user interface accordingly
             */
            function catValuesOfPlayers() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/catValuesOfPlayers"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    for (var j=1; j<6; j++){
                        $("#valueCatPlayer" + j).text("");
                    }
                    console.log(responseText);
                    for (var i=0; i<responseText.length; i++){
                        $("#valueCatPlayer"+(i+1)).text(parseInt(responseText[i]));
                    }

                    //update number of cards in communal pile
                    getNumOfCardsInComPile();
                };
                xhr.send();
            }


            /**
             * sends the chosen category to the backend when the user clicked on the category button in the user interface
             * @param category
             */
            function humanPlayerChosenCategory(category) {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/humanPlayerChosenCategory?category="+category); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // gets the chosen category back
                    $('#chosenCategory').text(responseText); //update user interface accordingly
                    showSelectedCategory(); //show the selected category in the user interface
                };
                xhr.send();
            }

            /**
             * sends info to database the one of the AIs needs to choose a category
             */
            function getAIchosenCategory() {
                var xhr = createCORSRequest('PUT', "http://localhost:7777/toptrumps/getAIchosenCategory"); // Request type and URL+parameters
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = xhr.response; // the text of the response
                    $('#chosenCategory').text(responseText); //change user interface with chosen category
                };
                xhr.send();
            }

            //FUNCTIONS TO UPDATE MIDDLE SECTION OF THE WEBPAGE

            /**
             * show the round result with names of players and their values in the chosen category
             * hide all other middle parts
             */
            function showRoundResult() {
			    $('#firstRound').hide();
                $('#draw').hide();
                $('#playersTurn').hide();
                $("#chosenCat").hide();
                $('#round').show();
            }

            /**
             * show middle screen to give user an indication the a draw occurred
             */
            function showDrawOccurred() {
			    $('#firstRound').hide();
                $('#round').hide();
                $('#playersTurn').hide();
                $("#chosenCat").hide();
                $('#draw').show();
            }

            /**
             * show the user the interface in that he can choose a category
             * hide all other middle parts
             */
            function showChooseCategory() {
			    $('#firstRound').hide();
                $('#round').hide();
                $('#draw').hide();
                $("#chosenCat").hide();
                $('#playersTurn').show();
            }

            /**
             * show the chosen category
             * hide rest of middle sections
             */
            function showSelectedCategory() {
                $('#firstRound').hide();
                $('#round').hide();
                $('#draw').hide();
                $('#playersTurn').hide();
                $("#chosenCat").show();
            }

            //FUNCTIONS TO CALL API REQUEST ACCORDING TO THE FLOW OF THE GAME

            /**
             * set up first round
             */

            function startSetup() {
                numberOfPlayers();
                $("#round").hide();
                $("#draw").hide();
                $("#playersTurn").hide();
                $("#chosenCat").hide();
                $(".gameEnded").hide();
                playerNamesAndNumOfCards();
                cardCatNames();
                roundCount();
                getNumOfCardsInComPile();
                getFirstCardDescription();
                getFirstCardValues();

            }

            /**
             * call functions to update user interface and get needed information
             */
            function roundResult() {
                getRoundWinner();
                catValuesOfPlayers();
                numberOfPlayers();
                playerNamesAndNumOfCards();
            }

			// This is a reusable method for creating a CORS request. Do not edit this.
			function createCORSRequest(method, url) {
  				var xhr = new XMLHttpRequest();
  				if ("withCredentials" in xhr) {

    				// Check if the XMLHttpRequest object has a "withCredentials" property.
    				// "withCredentials" only exists on XMLHTTPRequest2 objects.
    				xhr.open(method, url, true);

  				} else if (typeof XDomainRequest != "undefined") {

    				// Otherwise, check if XDomainRequest.
    				// XDomainRequest only exists in IE, and is IE's way of making CORS requests.
    				xhr = new XDomainRequest();
    				xhr.open(method, url);

 				 } else {

    				// Otherwise, CORS is not supported by the browser.
    				xhr = null;

  				 }
  				 return xhr;
			}
		</script>
		</body>
</html>