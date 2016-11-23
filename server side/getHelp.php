<?php
include 'getSaver.php';
include 'sendHelpNotification.php';
$location_x=$_POST['$location_x'];
$location_y=$_POST['$location_y'];
try {
    $art=new getSaver();
    $response=  array();
    if($response=$art->retreve(0,0))
    {
      $send=new sendHelpNotification();
$send->send($response,$location_x,$location_y);
    }
else{echo "false";}
} catch (Exception $exc) {
    echo $exc->getMessage();
}
?>