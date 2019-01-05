<?php
header('Content-Type: application/json; charset=utf-8');

$connection = mysqli_connect('localhost', 'root', '', 'online_shop');
$connection->set_charset('utf8');

$reqt = json_decode(file_get_contents('php://input'), true);

if ($reqt['type'] == 'select') {
	$data = mysqli_query($connection, $reqt['query']);
	$source = [];
	while ($row = mysqli_fetch_assoc($data)) {
		$source[] = $row;
	}

	$result = [ 'result' => $source ];
	echo json_encode($result, JSON_UNESCAPED_UNICODE);
}
