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

    <!-- CSS of the selection screen -->
    <style>
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

        body {
            background: #b5b5b5;
            color: black;
            max-width: 3072px;
            position: relative;
            margin: 0;
            padding-bottom: 6rem;
            min-height: 100%;
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
		Button animation styles
		 */
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

		/*
		Play Game button animation
		 */
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

		/*
		Statistics button animation
		 */
        a.animateButton.statsButton {
            border: 2px solid #000000;
        }
        a.animateButton.statsButton:after {
            background: #d6b945;
            -moz-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            -ms-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            -webkit-transform: translateX(-50%) translateY(-50%) rotate(25deg);
            transform: translateX(-50%) translateY(-50%) rotate(25deg);
        }
    </style>

    <!-- HTML of the selection screen -->

    <nav class="navbar navbar-expand-lg navbar-inverse bg-inverse">
        <a class="navbar-brand" href="http://localhost:7777/toptrumps">
            <img src="https://vignette.wikia.nocookie.net/logopedia/images/0/08/Top_Trumps.svg/revision/latest?cb=20160628161856" width="80" height="40" alt="Logo">
        </a>
        <a class="brand-name navbar-left">The world's best card game!</a>
    </nav>


    <div class="container body centreEverything">
		<div class="col-md-12 text-center">
			<h1>Welcome to TopTrumps</h1>
			<h3>Do you want to play a game or do you want to view the statistics?</h3>
		</div>

		<div class = "col-md-6 centerButtons">
        	<a href="http://localhost:7777/toptrumps/game" onclick="startGame()" id="playGameButton" class="btn animateButton statsButton">Play Game</a>
        </div>

		<div class = "col-md-6 centerButtons">
			<a href="http://localhost:7777/toptrumps/stats" id="showStatisticsButton" class="btn animateButton playButton">Show Statistics</a>
		</div>

    </div>

    <div class="footer">powered by THE GOAT GAMERS</br>Rebekka Orth - Lisa Laux - Vincent Schlatt - Neil Kennedy - Liang Shan
    </div>

    <!-- JavaScript of the selection screen -->

    <script type="text/javascript">


        /**
         * called when user clicks on "play game"
         * starts a new game and sets up a new game
         */
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