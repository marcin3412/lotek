<?php

session_start(); 

if (!isset($_SESSION['ssid'])) {       
    header('Location: index.php');         
}



echo 'Strona odczytana '.$_SESSION['count'].' razy w ciągu tej sesji';

?>
