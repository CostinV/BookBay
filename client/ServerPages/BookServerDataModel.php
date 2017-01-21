<?php


	
class Book{
	
	  public $bookName;
	  public $price = 0;
	  public $authorName;
	  public $publisher;
	  public $year = 0;
	  public $isbn_no;
	  public $genre;
	  public $bookCondition;
}




class StudentConstraint {
	public $minISBN = -1;
	public $maxISBN = -1;
	public $bookname="";
	public $nameExact = false;
	public $aname="";
	public $anameExact = false;
	public $pname="";
	public $pnameExact = false;
	public $minPrice = 0;
	public $maxPrice = -1;
	public $minYear = 0;
	public $maxYear = -1;
	public $genreName="";
	public $genrenameExact=false;
	public $recordStart = 0;
}


class DB{
	
	private $connection;
	
	function __construct() {
		
		$this->connection   = new mysqli("localhost", "andy", "pass");


	
/*
 * This is the "official" OO way to do it,
 * BUT $connect_error was broken until PHP 5.2.9 and 5.3.0.
 */

if (mysqli_connect_error()) {
    die('Connect Error (' . mysqli_connect_errno() . ') '
            . mysqli_connect_error());
}

$this->connection->select_db('andy_db');

	}
	
	function __destruct() {
		$this->connection->close();
	}
	
	public function delete($ssn)
	{
		$query = "delete from student where ssn=".$ssn;
		$this->connection->query($query);
		
	}
	
	public function edit($student)
	{
		if($student->year < 1 || $student->year > 4 || $student->gpa <0 ||$student->gpa>4
		||$student->ssn < 100000000 || $student->ssn > 999999999)
			return false;
		
		
		$query = "select id from majors where name like \"".$student->major."\"";
		if($result = $this->connection->query($query))
		{
				
			$mid = -1;
			if($result->num_rows > 0)
			{
				//printf("mid is ".$mid);
				$row = $result->fetch_array();
				$mid = $row['id'];
	
				//	printf("mid is ".$mid);
			}
			else
			{
				$query = "insert into majors values (NULL, \"".$student->major."\")";
				if($this->connection->query($query))
				{
					$mid = $this->connection->insert_id;
						
					//printf("mid is ".$mid);
				}
				else
				{
					//printf("major insert failed");
					return false;
				}
			}
				
			if($mid < 1)
			return false;
				
			$query = "update students set ssn=".$student->ssn
			.", name=\"".$student->name
			."\", gpa=".$student->gpa
			.", year=".$student->year
			.", mid=".$mid
			." where ssn=".$student->ssn;
				
			if($this->connection->query($query))
			{
				return true;
			}
			
				
		}
		
		return false;
	}
	
	public function add($student)
	{
		if($student->year < 1 || $student->year > 4 || $student->gpa <0 ||$student->gpa>4
		||$student->ssn < 100000000 || $student->ssn > 999999999)
			return false;
		
		$query = "select id from majors where name like \"".$student->major."\"";
		if($result = $this->connection->query($query))
		{
			
			$mid = -1;
			if($result->num_rows > 0)
			{
				//printf("mid is ".$mid);
				$row = $result->fetch_array();
				$mid = $row['id'];	
				
			//	printf("mid is ".$mid);
			}
			else
			{
				$query = "insert into majors values (NULL, \"".$student->major."\")";
				if($this->connection->query($query))
				{
					$mid = $this->connection->insert_id;
					
					//printf("mid is ".$mid);
				}
				else
				{ 
					//printf("major insert failed");
					return false;
				}
			}
			
			if($mid < 1)
				return false;
			
			$query = "insert into students values (".$student->ssn
			.", ".$student->year
			.", ".$student->gpa
			.", \"".$student->name
			."\", ".$mid
			.")";
			
			if($this->connection->query($query)&&$this->connection->affected_rows > 0)
			{
				return true;
			}
		
			
		}
		
		return false;
	}
	
	public function echoAll($forWeb=false)
	{
		
		$returnArray = array();
		if($res = $this->connection->query("select A.ssn, A.year, A.gpa, A.name, B.name from students A, majors B where A.mid = B.id"))
		{
			while($row = $res->fetch_array(MYSQLI_NUM))
			{
				
				if($forWeb)
				{
				foreach($row as $value)
				{
					printf($value." ");
				}
				printf("<br>");
				}
				else
				{
				foreach($row as $value)
				{
					printf($value."\n");
				}
				}
				
			}
		}
		
	} 
	
	public function echoSome($constraint, $forWeb = false)
	{
	
		$query = "select A.ssn, A.year, A.gpa, A.name, B.name  from students A, majors B where A.mid = B.id"
				." AND A.ssn >= ".$constraint->minSSN
				." AND A.gpa >= ".$constraint->minGPA
				." AND A.gpa <= ".$constraint->maxGPA
				." AND A.year >= ".$constraint->minYear;
		
		if($constraint->maxSSN > -1)
			$query .= " AND A.ssn <= ".$constraint->maxSSN;
		if($constraint->maxYear > -1)
			$query .= " AND A.year <= ".$constraint->maxYear;
		if($constraint->name != "")
		{
		if($constraint->nameExact)
			$query .= " AND A.name LIKE \"".$constraint->name."\"";
		else 
			$query .= " AND A.name LIKE \"%".$constraint->name."%\"";
		}
		if($constraint->majorName != "")
		{
		if($constraint->majorNameExact)
			$query .= " AND B.name LIKE \"".$constraint->majorName."\"";
		else
			$query .= " AND B.name LIKE \"%".$constraint->majorName."%\"";
		}
		
				
		$returnArray = array();
		if($res = $this->connection->query($query))
		{
			while($row = $res->fetch_array(MYSQLI_NUM))
			{
	
				if($forWeb)
				{
				foreach($row as $value)
				{
					printf($value." ");
				}
				printf("<br>");
				}
				else
				{
				foreach($row as $value)
				{
					printf($value."\n");
				}
				}
			}
		}
	
	}
	
	
	
	
}


?>