<?php
class sendHelpNotification{

function __construct(){
}
function sendMessageThroughFCM($registatoin_ids, $data)
{
    $url = 'https://fcm.googleapis.com/fcm/send';
    $fields = array(
        'registration_ids' =>   $registatoin_ids   ,
        'data' => $data,
    );
    //************   rplace this with your api key ***************// 
    define("GOOGLE_API_KEY", "AIzaSyAIESMYOfEiVxWS8fzYVnU-cFIBwadFqpU");
    $headers = array(
        'Authorization: key=' . GOOGLE_API_KEY,
        'Content-Type: application/json'
    );
    $ch = curl_init();
    curl_setopt($ch, CURLOPT_URL, $url);
    curl_setopt($ch, CURLOPT_POST, true);
    curl_setopt($ch, CURLOPT_HTTPHEADER, $headers);
    curl_setopt($ch, CURLOPT_RETURNTRANSFER, true);
    curl_setopt($ch, CURLOPT_SSL_VERIFYHOST, 0);
    curl_setopt($ch, CURLOPT_SSL_VERIFYPEER, false);
    curl_setopt($ch, CURLOPT_POSTFIELDS, json_encode($fields));
    $result = curl_exec($ch);
    if ($result === FALSE) {
        die('Curl failed: ' . curl_error($ch));
    }
    curl_close($ch);
   // echo $result;

 }			

function send($tokens,$location_x,$location_y){
     
$data = array('title' =>"saveme",'location_x' =>$location_x,'location_y' =>$location_y);
         
      $this->sendMessageThroughFCM($tokens, $data);
   
}
 
 

}