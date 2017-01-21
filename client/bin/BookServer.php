<?php

require 'BookServerDataModel.php';
$db = new DB();

if(ISSET($_GET["getAll"]))
{
$forWeb = ISSET($_GET["forWeb"]);
$db->echoAll($forWeb);

}
else if(ISSET($_POST["name"])&&ISSET($_POST["ssn"])&&ISSET($_POST["year"])&&ISSET($_POST["gpa"])&&ISSET($_POST["major"]))
{
	$student = new Student();
	
	$student->name = $_POST["name"];
	$student->gpa = $_POST["gpa"];
	$student->year = $_POST["year"];
	$student->ssn = $_POST["ssn"];
	$student->major = $_POST["major"];
	
	if(ISSET($_POST["edit"]))
	{
		if($db->edit($student))
		{
			printf("true");
		}
		else
		{
			printf("false");
		}
	}
	
	else if(ISSET($_POST["add"]))
	{
		if($db->add($student))
		{
			printf("true");
		}
		else
		{
			printf("false");
		}
	}
	
}
else if(ISSET($_POST["delete"])&&ISSET($_POST["ssn"]))
{
	$db->delete($_POST["ssn"]);
}
else{
	$constraint = new StudentConstraint();
	if(ISSET($_GET["minSSN"])&&""!=$_GET["minSSN"])
	{
		$constraint->minSSN = $_GET['minSSN'];
	}
	if(ISSET($_GET["maxSSN"]) && ""!=$_GET["maxSSN"] &&$_GET["maxSSN"]>-1)
	{
		$constraint->maxSSN = $_GET['maxSSN'];
	}
	if(ISSET($_GET["name"])&&""!=$_GET["name"])
	{
		$constraint->name = $_GET['name'];
	}
	if(ISSET($_GET["nameExact"])&&$_GET["nameExact"]=="true")
	{
		$constraint->nameExact = true;
	}
	else
	{
		$constraint->nameExact = false;
	}
	if(ISSET($_GET["minGPA"])&&""!=$_GET["minGPA"])
	{
		$constraint->minGPA = $_GET['minGPA'];
	}
	if(ISSET($_GET["maxGPA"])&&""!=$_GET["maxGPA"])
	{
		$constraint->maxGPA = $_GET['maxGPA'];
	}
	if(ISSET($_GET["minYear"])&&""!=$_GET["minYear"])
	{
		$constraint->minYear = $_GET['minYear'];
	}
	if(ISSET($_GET["maxYear"])&&""!=$_GET["maxYear"])
	{
		$constraint->maxYear = $_GET['maxYear'];
	}
	if(ISSET($_GET["majorName"])&&""!=$_GET["majorName"])
	{
		$constraint->majorName = $_GET['majorName'];
	}
	if(ISSET($_GET["majorNameExact"])&&$_GET["majorNameExact"]=="true" )
	{
		$constraint->majorNameExact = true;
	}
	else
	{
	$constraint->majorNameExact = false;
	}
	if(ISSET($_GET["recordStart"])&&""!=$_GET["recordStart"])
		$constraint->recordStart = $_GET['recordStart'];
	
	$forWeb = false;
	if(ISSET($_GET["forWeb"]))
		$forWeb = true;
	
	$db->echoSome($constraint, $forWeb);


}


?>
