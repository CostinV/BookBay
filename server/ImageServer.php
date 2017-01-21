<?php

if (ISSET($_GET["add"])) {
	if (ISSET($_GET["isbn"])) {
		$filename="images/".$_GET["isbn"].".jpg";
		if (file_exists($filename)) {
			printf("Already Exists");
			return;
		}
		$fileData=file_get_contents('php://input');
		$fhandle=fopen($filename, 'wb');
		fwrite($fhandle, $fileData);
		fclose($fhandle);
		printf("Done Uploading");
	}
}

if (ISSET($_POST["remove"])) {
	if (ISSET($_POST["isbn"])) {
		$filename="images/".$_POST["isbn"].".jpg";
		if (file_exists($filename)) {
			unlink($filename);
		}
		printf("true");
	}
}

?>