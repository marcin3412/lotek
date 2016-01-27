<html>
		<!--
    	SEKCJA INCLUDE
    	-->
    <head> 	
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" >
		<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
		<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    </head>
    	<!--
		KONIEC SEKCJI INCLUDE
		-->

    <body>
    	<!-----------
    		SEKCJA TOPMENU
    	------------>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
        <span class="sr-only">Toggle navigation</span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="#">Totolotek</a>
    </div>

    
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li class="active"><a href="index.html">Strona Główna <span class="sr-only">(current)</span></a></li>
        <li><a href="saldo.php"> Moje saldo </a></li>
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Statystyki <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="wygrane.php">Moje ostatnie wygrane</a></li>
            <li><a href="kumulacja.php">Kumulacja</a></li>
            <li><a href="kumulacja-historia.php">Historia Kumulacji</a></li>
          </ul>
        </li>
      </ul>
      <form class="navbar-form navbar-left" role="search">
        <div class="form-group">
          <input type="text" class="form-control" placeholder="Wpisz numer losu" disabled="disabled">
        </div>
        <button type="submit" class="btn btn-default" disabled="disabled">Szukaj losu</button>
      </form>
     <p class="navbar-text navbar-right">Zalogowany jako <a href="#" class="navbar-link">$User</a></p>
      <ul class="nav navbar-nav navbar-right">
        
        <li class="dropdown">
          <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Panel Użytkownika <span class="caret"></span></a>
          <ul class="dropdown-menu">
            <li><a href="edit-profile.php">Edycja Profilu</a></li>
            <li><a href="show-profile.php">Podgląd Profilu</a></li>
            <li><a href="email-change.php">Zmiana Email</a></li>
            <li><a href="register.php">Zaloguj</a></li>
            <li><a href="logout.php">Wyloguj</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
		<!-----------
			KONIEC SEKCJI TOPMENU
		------------>


<form id="rev-pass" class="container">
  <div class="form-group">
    <label class="col-sm-2 control-label ">Email</label>
    <div class="col-sm-10">
      <input type="email" class="form-control" name="email" placeholder="Email">
    </div>
  </div>
  <div class="form-group">
    <label  class="col-sm-2 control-label">Hasło</label>
    <div class="col-sm-10">
      <input type="password" class="form-control" name="haslo" placeholder="Hasło">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="submit" class="btn btn-default" name="resetuj">Resetuj Hasło</button>
    </div>
  </div>
</form> 
</div>


    </body>
</html>
