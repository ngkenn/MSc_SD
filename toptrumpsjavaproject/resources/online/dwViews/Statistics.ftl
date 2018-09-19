<html xmlns="http://www.w3.org/1999/html">

	<head>
		<!-- Web page title -->
    	<title>Top Trumps - Statistics</title>
    	
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

        <!-- Define the CSS Style of the different components -->
		<style>

            /* Navbar and Footer */
            .navbar {
                background-color: #434343;
                color: #d6b945;
                text-align: center
            }

            a.brand-name {
                font-size: 2.125rem;
                font-family: 'Arial Rounded MT Bold', Arial, sans-serif;
            }

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

            /* The page body */
            body {
                background: #b5b5b5;
                color: black;
                max-width: 3072px;
                position: relative;
                margin: 0;
                padding-bottom: 6rem;
                min-height: 100%;
            }

            /* The styles for each row */
            .row.top {
                float: top;
            }

			.row.middle {
                align-items: center;
				background-color: #d6b945;
                float: inside;
			}

			.row.bottom {
				padding-top: 15px;
                float: bottom;
			}

            /* Clear floats after the last row */
            .row.bottom:after {
                content: "";
                display: table;
                clear: both;
            }

            /* The styles for each column */
            .col {
                text-align: center;
                font-family: "Arial Rounded MT Bold";
                float: left;
                padding: 15px;
			}

            /* Define the button style/actions */
            .centerButtons{
                margin:0 auto;
                float:left;
            }

            a.animateButton:link, a.animateButton:visited {
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
            a.animateButton:link:after, a.animateButton:visited:after {
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
            a.animateButton:link:hover, a.animateButton:visited:hover {
                color: black;
                text-shadow: none;
            }
            a.animateButton:link:hover:after, a.animateButton:visited:hover:after {
                height: 450%;
            }
            a.animateButton:link, a.animateButton:visited {
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

            /* Play Game button animation */
            a.animateButton.playButton {
                border: 2px solid #000000;
            }
            a.animateButton.playButton:after {
                background: #d6b945;
                -moz-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                -ms-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                -webkit-transform: translateX(-50%) translateY(-50%) rotate(-25deg);
                transform: translateX(-50%) translateY(-50%) rotate(-25deg);
            }
		</style>

    <!-- HTML of the statistics screen -->

        <!-- The navbar functionality -->
        <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
            <a class="navbar-brand" href="http://localhost:7777/toptrumps">
                <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
            </a>
            <a class="brand-name navbar-left">The world's best card game!</a>
        </nav>

        <!-- Define each row and column in turn. -->
        <!-- There are 3 rows with 3 columns each. -->
		<div class="row top" style="padding-top: 30px">
            <div class="col"></div>
			<div class="col">
                <p style="font-size: 20px; font-family: Arial">Total games played: <strong id="totalGameCount"></strong> </p>
                <br>
                <p style="font-size: 20px; font-family: Arial">Average number of draws: <strong id="averageDrawCount"></strong> </p>
                <br>
                <p style="font-size: 20px; font-family: Arial">Highest number of rounds per game: <strong id="highestNumberOfRoundCount"></strong> </p>
                <br>
			</div>
            <div class="col"></div>
		</div>

    	<div class="row middle">
            <div class="col">
                <p style="font-size: 40px"><strong>HUMAN</strong></p>
                <p>
                    <img src="https://image.flaticon.com/icons/svg/453/453376.svg" alt="Human Icon" width="150" height="190">
                </p>
                <p style="font-size: 40px"><strong id="humanWinCount"></strong></p>
            </div>
			<div class="col">
                <p><strong style="font-size: 100px">vs.</strong></p>
			</div>
			<div class="col">
                <p style="font-size: 40px"><strong>AI</strong></p>
                <p>
                    <img src="https://image.flaticon.com/icons/svg/653/653507.svg" alt="AI Icon" width="150" height="190">
                </p>
                <p style="font-size: 40px"><strong id="AIWinCount"></strong></p>
			</div>
    	</div>

    	<div class="row bottom">
			<div class="col"></div>
			<div class="col centerButtons">
                <!-- On clicking the button, the user is redirected to the GameScreen -->
                <a href="http://localhost:7777/toptrumps/game" onclick="startGame()" id="playGameButton" class="btn animateButton playButton">Play Again</a>
			</div>
            <div class="col"></div>
    	</div>

    <div class="footer">powered by THE GOAT GAMERS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>

    <!-- JavaScript of the statistics screen -->

		<script type="text/javascript">
		
			// Method that is called on page load
			function initalize() {
			
				getTotalGame();
				getAverageDraw();
				getHighestNumberOfRound();
				getHumanWin();
				getAIWin();
			}

			/**
             * Start the game data request
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

            /**
             * Get the number of total games played so far
             * */
			function getTotalGame() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getTotalGame"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#totalGameCount').text(parseInt(responseText));
                };
                xhr.send();
            }

            /**
             * Get the average number of draws occurred so far
             * */
            function getAverageDraw() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAverageDraw"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#averageDrawCount').text(parseInt(responseText));
                };
                xhr.send();
            }

            /**
             * Get the highest number of rounds played so far
             * */
            function getHighestNumberOfRound() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHighestNumberOfRound"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#highestNumberOfRoundCount').text(parseInt(responseText));
                };
                xhr.send();
            }

            /**
             * Get the number of wins for the Human Player
             * */
            function getHumanWin() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getHumanWin"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#humanWinCount').text(parseInt(responseText));
                };
                xhr.send();
            }

            /**
             * Get the number of wins for all AI Players
             * */
            function getAIWin() {
                var xhr = createCORSRequest('GET', "http://localhost:7777/toptrumps/getAIWin"); // Request type and URL
                if (!xhr) {
                    alert("CORS not supported");
                }
                xhr.onload = function(e) {
                    var responseText = JSON.parse(xhr.response); // the text of the response
                    $('#AIWinCount').text(parseInt(responseText));
                };
                xhr.send();
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