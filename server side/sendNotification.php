<?php
class sendNotification{

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

function send($title,$describtion,$iamge,$type){
     
   $con1= mysqli_connect("localhost","u704613033_deeb","deeb1221995","u704613033_artic");
$data = array('title' =>$title,'describtion' =>$describtion,'iamge' =>$iamge,'type' =>$type);
          $query="select *from tokens;";
$result=  mysqli_query($con1,$query);
$count=1;
$tokens_arr =  array();
while($row=  mysqli_fetch_array($result))

	{if($count>=1000)
       {
      $this->sendMessageThroughFCM($tokens_arr, $data);
      unset($tokens_arr);
         }
    array_push($tokens_arr ,$row[1]);
$count++;
	}


    //************   rplace this with your key values data or modifi it as you like ***************//
 
 $this->sendMessageThroughFCM($tokens_arr, $data);
}
 
 

}