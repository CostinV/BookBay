<?php


	
class Book{
	public $isbn = "";
	public $title = "";
	public $author = "";
	public $publisher = "";
	public $genre = "";
	public $year = 0;
	public $seller_name = "";
	public $price = 0;
	public $buynow = 0;
	public $quantity = -1;
	public $book_condition = "";
}

class User{
	public $login_name;
	public $password_hash;
	public $display_name;
	public $warnings=0;
	public $credits=0;
	public $suspended=0;
	public $rating=50;
}


class BookConstraint {
	public $isbn = "";
	public $title= "";
	public $titleExact = false;
	public $author= "";
	public $authorExact = false;
	public $publisher = "";
	public $publisherExact = false;
	public $minPrice = 0;
	public $maxPrice = -1;
	public $minYear = 0;
	public $maxYear = -1;
	public $genre="";
	public $genreExact=false;
	public $seller_name="";
	public $sellerExact=false;
}


class Review {
	public $book_isbn;
	public $book_title;
	public $book_condition;
	public $seller_name;
	public $buyer_name;
	public $book_stars;
	public $book_comments;
	public $seller_stars;
	public $seller_comments;
	public $buyer_stars;
	public $buyer_comments;
	public $reviewed_by_buyer = 0;
	public $reviewed_by_seller = 0;
}

class BookDB{
	
	private $connection;
	
	function __construct() {
		
		$this->connection   = new mysqli("localhost", "root", "");

if (mysqli_connect_error()) {
    die('Connect Error (' . mysqli_connect_errno() . ') '
            . mysqli_connect_error());
}

$this->connection->select_db('bookbay');

	}
	
	function __destruct() {
		$this->connection->close();
	}
	
	
	public function edit($book)
	{
		$query = "SELECT quantity FROM books WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
		if($result = $this->connection->query($query)) {
			if($result->num_rows < 1) {
				printf("false");
			} else {
				$edit = "UPDATE books SET ";
				if ($book->title != "") 
					$edit = $edit."title='".$book->title."', ";
				if ($book->author != "") 
					$edit = $edit."author='".$book->author."', ";
				if ($book->year > 999) 
					$edit = $edit."year='".$book->year."', ";
				if ($book->publisher != "") 
					$edit = $edit."publisher='".$book->publisher."', ";
				if ($book->genre != "") 
					$edit = $edit."genre='".$book->genre."', ";
				if ($book->price != 0) 
					$edit = $edit."price='".$book->price."', ";
				if ($book->buynow != 0) 
					$edit = $edit."buynow='".$book->buynow."', ";
				if ($book->quantity != -1) 
					$edit = $edit."quantity='".$book->quantity."', ";
				$edit = substr($edit, 0, -2);
				$edit = $edit." WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
				if ($this->connection->query($edit))
					printf("true");
				else
					printf("false");
			}
		}
	}
	
	public function add($book)
	{
		$query = "SELECT quantity FROM books WHERE isbn='".$book->isbn."'"." AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
		if($result = $this->connection->query($query))
		{
			
			if($result->num_rows > 0)
			{
				$row = $result->fetch_array();
				$q = $row['quantity'] + $book->quantity;
				$addquery = "UPDATE books SET quantity='".$q."' WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
				if ($this->connection->query($addquery)) {
					printf("true");
				}
			}
			else
			{
				$addquery = "INSERT INTO books (isbn, title, author, year, publisher, genre, seller_name, price, buynow, quantity, book_condition) VALUES ('".$book->isbn
				."', '".$book->title
				."', '".$book->author
				."', '".$book->year
				."', '".$book->publisher
				."', '".$book->genre
				."', '".$book->seller_name
				."', '".$book->price
				."', '".$book->buynow
				."', '".$book->quantity
				."', '".$book->book_condition
				."')";
				if ($this->connection->query($addquery)) {
					printf("true");
					//printf("%s", $addquery);
				}
			}
		}
		//printf("false");
	}
	
	public function remove($book)
	{
		$query = "SELECT quantity FROM books WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
		if($result = $this->connection->query($query)) {
			if($result->num_rows < 1) {
				printf("false");
			} else {
				$remove_one = "UPDATE books SET quantity = quantity - 1 WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
				if($this->connection->query($remove_one)) {
					$recheck = "SELECT quantity FROM books WHERE isbn='".$book->isbn."' AND quantity <> 0";
					if($rs = $this->connection->query($recheck)) {
						if($rs->num_rows < 1) {
							printf("gone");
						} else {
							printf("true");
						}
					}
				}
			}
		} else {
			printf("false");
		}
	}
	
	public function removeSuspendedBook($book) {
		$remove = "DELETE FROM suspendedbooks WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."'";
		$this->connection->query($remove);
		$remove2 = "DELETE FROM complaints WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."'";
		if ($this->connection->query($remove2))
			printf("true");

	}
	
	public function removeUserComplaints($display_name) {
		$remove = "UPDATE users SET warnings='0', suspended='0' WHERE display_name='".$display_name."'";
			if($this->connection->query($remove)) {
				printf("%s",$remove);
				printf("true");
			}
	}
	
	public function removeOne($book)
	{
		$query = "SELECT quantity FROM books WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
		if($result = $this->connection->query($query)) {
			if($result->num_rows < 1) {
				printf("false");
			}	else {
				$row = $result->fetch_assoc();
				if ($row["quantity"] == "1") {
					$remove_one = "UPDATE books SET quantity = quantity - 1 WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
					if($this->connection->query($remove_one)) {
						$this->removeBids($book);
						$recheck = "SELECT quantity FROM books WHERE isbn='".$book->isbn."' AND quantity <> 0";
						if($rs = $this->connection->query($recheck)) {
							if($rs->num_rows < 1) {
								printf("gone");
							} else {
								printf("true");
							}
						}
					}
				} else {
					$remove_one = "UPDATE books SET quantity = quantity - 1 WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
					if ($this->connection->query($remove_one)) {
						printf("true");
					}
				}
			}
		} 
	}
	
	public function removeBids($book) {
		$search = "SELECT book_isbn, seller_name, book_condition, buyer_name FROM bids WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."' AND buy_now='0'";
		if ($result = $this->connection->query($search)) {
			if($result->num_rows > 0) {
				while ($row = $result->fetch_assoc()) {
					$book = new Book();
					$book->isbn = $row["book_isbn"];
					$book->seller_name = $row["seller_name"];
					$book->book_condition = $row["book_condition"];
					$buyer_name = $row["buyer_name"];
					$this->cancelBid($book, $buyer_name);
				}
			}
		}
	}
	
	/*
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
		
	} */
	
	
	public function getHighestBid($book) {
		$query = "SELECT MAX(bid_amount) AS bid FROM bids WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."' AND buy_now='0'";
		if ($result = $this->connection->query($query)) {
			if($result->num_rows < 1) {
				printf("none");
			} else {
				$rs = $result->fetch_assoc();
				printf("%s",$rs["bid"]);
			}
		} else {
			printf("false");
		}
	}
	
	public function postBid($book, $buyer_name, $buy_now) {
	
		$check = "SELECT credits FROM users WHERE display_name='".$buyer_name."'";
		if ($result = $this->connection->query($check)) {
			if($result->num_rows > 0) {
				$row = $result->fetch_array();
				if ($book->price > $row["credits"]) {
					printf("false");
					return;
				}
			} else {
				printf("false");
				return;
			}
		}
		
			if ($buy_now == "true")	 {
				$this->acceptBid($book, $buyer_name);
				printf("true");
				return;
			}
		
			$query = "INSERT INTO bids (book_isbn, book_title, seller_name, book_condition, buyer_name, bid_amount, buy_now) VALUES ('".$book->isbn
					."', '".$book->title
					."', '".$book->seller_name
					."', '".$book->book_condition
					."', '".$buyer_name
					."', '".$book->price;
	
				$query = $query."', '0')";
			if ($this->connection->query($query)) {
				$credits = "UPDATE users SET credits = credits - ".$book->price." WHERE display_name='".$buyer_name."'";
				if ($this->connection->query($credits))
					printf("true");
				else
					printf("false");
			}
	}
	
	public function addCredits($user) {
		$credits = "UPDATE users SET credits = credits + ".$user->credits." WHERE display_name='".$user->display_name."'";
		if ($this->connection->query($credits))
					printf("true");
				else
					printf("false");
	}
	
	public function getHighestBidAmount($book) {
		$query = "SELECT MAX(bid_amount) AS bid FROM bids WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."' AND buy_now='0'";
		if ($result = $this->connection->query($query)) {
			$rs = $result->fetch_assoc();
			return $rs["bid"];
		}
	}
	
	public function getCredits($display_name) {
		$query = "SELECT credits FROM users WHERE display_name='".$display_name."'";
		if ($result = $this->connection->query($query)) {
			$rs = $result->fetch_assoc();
			printf("%s", $rs["credits"]);
		}
		else
			printf("false");
	}
	
	public function cancelBid($book, $buyer_name) {
		$query = "DELETE FROM bids WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."' AND buyer_name='".$buyer_name."'";
		if ($this->connection->query($query)) {
			$credits = "UPDATE users SET credits = credits + ".$book->price." WHERE display_name='".$buyer_name."'";
				if ($this->connection->query($credits))
					printf("true");
			
			$history = "INSERT INTO bidhistory (book_isbn, book_title, seller_name, book_condition, buyer_name, bid_amount) VALUES ('".$book->isbn
				."', '".$book->title
				."', '".$book->seller_name
				."', '".$book->book_condition
				."', '".$buyer_name
				."', '".$book->price
				."')";
			$this->connection->query($history);
		} else
			printf("false");
	}
	
	public function removeBid($book, $buyer_name) {
		$query = "DELETE FROM bids WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."' AND buyer_name='".$buyer_name."'";
		$this->connection->query($query);
			$history = "INSERT INTO bidhistory (book_isbn, book_title, seller_name, book_condition, buyer_name, bid_amount) VALUES ('".$book->isbn
				."', '".$book->title
				."', '".$book->seller_name
				."', '".$book->book_condition
				."', '".$buyer_name
				."', '".$book->price
				."')";
			$this->connection->query($history);
	}
	
	
	public function acceptBid($book, $buyer_name) {
		$this->removeOne($book);
		$credits = "UPDATE users SET credits = credits + ".(($book->price)*0.95)." WHERE display_name='".$book->seller_name."'";
		$this->connection->query($credits);
		$review = new Review();
		$review->book_isbn = $book->isbn;
		$review->book_title = $book->title;
		$review->book_condition = $book->book_condition;
		$review->seller_name = $book->seller_name;
		$review->buyer_name = $buyer_name;
		$this->removeBid($book, $buyer_name);
		$this->addReview($review);
	}
	
	public function getBids($buysell, $display_name) {
		if ($buysell == "buy") {
			$query = "SELECT book_isbn, book_title, seller_name, book_condition, bid_amount FROM bids WHERE buyer_name='".$display_name."' AND buy_now='0' ORDER BY book_title ASC";
			if ($result = $this->connection->query($query)) {
				while($row = $result->fetch_assoc()) {
					$book = new Book();
					$book->isbn = $row["book_isbn"];
					$book->title = $row["book_title"];
					$book->seller_name = $row["seller_name"];
					$book->book_condition = $row["book_condition"];
					$book->price = $row["bid_amount"];
					$hb = $this->getHighestBidAmount($book);
					if ($book->price == $hb) {
						$hb = "1";
					} else {
						$hb = "0";
					}
					printf("%s\n", $book->isbn);
					printf("%s\n", $book->title);
					printf("%s\n", $book->seller_name);
					printf("%s\n", $book->book_condition);
					printf("%s\n", $book->price);
					printf("%s\n", $hb);
				}
			}
		} else {
			$query = "SELECT book_isbn, book_title, buyer_name, book_condition, bid_amount FROM bids WHERE seller_name='".$display_name."' AND buy_now='0' ORDER BY book_title ASC, bid_amount DESC";
			if ($result = $this->connection->query($query)) {
				while($row = $result->fetch_assoc()) {
					$book = new Book();
					$book->isbn = $row["book_isbn"];
					$book->title = $row["book_title"];
					$book->seller_name = $display_name;
					$book->book_condition = $row["book_condition"];
					$book->price = $row["bid_amount"];
					$hb = $this->getHighestBidAmount($book);
					if ($book->price == $hb) {
						printf("%s\n", $book->isbn);
						printf("%s\n", $book->title);
						printf("%s\n", $row["buyer_name"]);
						printf("%s\n", $book->book_condition);
						printf("%s\n", $book->price);
					}
				}
			}
		}
	}
	
	public function getBidHistory($display_name) {
		$query = "SELECT book_isbn, book_title, seller_name, book_condition, bid_amount FROM bidhistory WHERE buyer_name='".$display_name."'";
			if ($result = $this->connection->query($query)) {
				while($row = $result->fetch_assoc()) {
					$book = new Book();
					$book->isbn = $row["book_isbn"];
					$book->title = $row["book_title"];
					$book->seller_name = $row["seller_name"];
					$book->book_condition = $row["book_condition"];
					$book->price = $row["bid_amount"];
					
					printf("%s\n", $book->isbn);
					printf("%s\n", $book->title);
					printf("%s\n", $book->seller_name);
					printf("%s\n", $book->book_condition);
					printf("%s\n", $book->price);
				}
			}
	}
	
	public function getBrowseHistory($display_name) {
		$query = "SELECT isbn, title, author, year, publisher, genre, seller_name FROM browsehistory WHERE display_name='".$display_name."'";
			if ($result = $this->connection->query($query)) {
				while($row = $result->fetch_assoc()) {
					$book = new Book();
					$book->isbn = $row["isbn"];
					$book->title = $row["title"];
					$book->author = $row["author"];
					$book->year = $row["year"];
					$book->publisher = $row["publisher"];
					$book->genre = $row["genre"];
					$book->seller_name = $row["seller_name"];

					printf("%s\n", $book->isbn);
					printf("%s\n", $book->title);
					printf("%s\n", $book->author);
					printf("%s\n", $book->year);
					printf("%s\n", $book->publisher);
					printf("%s\n", $book->genre);
					printf("%s\n", $book->seller_name);
				}	
			}
	}
	
	public function getReviews($buysell, $display_name, $reviewed) {
		if ($buysell == "buy") {
			if ($reviewed > 0) {
				$query = "SELECT book_isbn, book_title, seller_name, book_condition, book_stars, book_comments, seller_stars, seller_comments, buyer_stars, buyer_comments 
				FROM reviews WHERE buyer_name='".$display_name."' AND reviewed_by_buyer > 0 ORDER BY book_title ASC";
				if ($result = $this->connection->query($query)) {
					while($row = $result->fetch_assoc()) {
						$review = new Review();
						$review->book_isbn = $row["book_isbn"];
						$review->book_title = $row["book_title"];
						$review->seller_name = $row["seller_name"];
						$review->book_condition = $row["book_condition"];
						$review->book_stars = $row["book_stars"];
						$review->book_comments = $row["book_comments"];
						$review->seller_stars = $row["seller_stars"];
						$review->seller_comments = $row["seller_comments"];
						$review->buyer_stars = $row["buyer_stars"];
						$review->buyer_comments = $row["buyer_comments"];

						printf("%s\n", $review->book_isbn);
						printf("%s\n", $review->book_title);
						printf("%s\n", $review->book_condition);
						printf("%s\n", $review->seller_name);
						printf("%s\n", $review->book_stars);
						printf("%s\n", $review->book_comments);
						printf("break\n");
						printf("%s\n", $review->seller_stars);
						printf("%s\n", $review->seller_comments);
						printf("break\n");
						printf("%s\n", $review->buyer_stars);
						printf("%s\n", $review->buyer_comments);
						printf("break\n");
					}
				}
			}
			else {
				$query = "SELECT book_isbn, book_title, seller_name, book_condition FROM reviews WHERE buyer_name='".$display_name."' AND reviewed_by_buyer = 0 ORDER BY book_title ASC";
				if ($result = $this->connection->query($query)) {
					while($row = $result->fetch_assoc()) {
						$review = new Review();
						$review->book_isbn = $row["book_isbn"];
						$review->book_title = $row["book_title"];
						$review->seller_name = $row["seller_name"];
						$review->book_condition = $row["book_condition"];
						//printf("printing\n");
						printf("%s\n", $review->book_isbn);
						printf("%s\n", $review->book_title);
						printf("%s\n", $review->book_condition);
						printf("%s\n", $review->seller_name);
					}
				}
			}
		} else {
			if ($reviewed > 0) {
				$query = "SELECT book_isbn, book_title, buyer_name, book_condition, book_stars, book_comments, seller_stars, seller_comments, buyer_stars, buyer_comments 
				FROM reviews WHERE seller_name='".$display_name."' AND reviewed_by_seller > 0 ORDER BY book_title ASC";
				if ($result = $this->connection->query($query)) {
					while($row = $result->fetch_assoc()) {
						$review = new Review();
						$review->book_isbn = $row["book_isbn"];
						$review->book_title = $row["book_title"];
						$review->buyer_name = $row["buyer_name"];
						$review->book_condition = $row["book_condition"];
						$review->book_stars = $row["book_stars"];
						$review->book_comments = $row["book_comments"];
						$review->seller_stars = $row["seller_stars"];
						$review->seller_comments = $row["seller_comments"];
						$review->buyer_stars = $row["buyer_stars"];
						$review->buyer_comments = $row["buyer_comments"];

						printf("%s\n", $review->book_isbn);
						printf("%s\n", $review->book_title);
						printf("%s\n", $review->book_condition);
						printf("%s\n", $review->buyer_name);
						printf("%s\n", $review->book_stars);
						printf("%s\n", $review->book_comments);
						printf("break\n");
						printf("%s\n", $review->seller_stars);
						printf("%s\n", $review->seller_comments);
						printf("break\n");
						printf("%s\n", $review->buyer_stars);
						printf("%s\n", $review->buyer_comments);
						printf("break\n");
					}
				}
			}
			else {
				$query = "SELECT book_isbn, book_title, buyer_name, book_condition FROM reviews WHERE seller_name='".$display_name."' AND reviewed_by_seller = 0 ORDER BY book_title ASC";
				if ($result = $this->connection->query($query)) {
					while($row = $result->fetch_assoc()) {
						$review = new Review();
						$review->book_isbn = $row["book_isbn"];
						$review->book_title = $row["book_title"];
						$review->buyer_name = $row["buyer_name"];
						$review->book_condition = $row["book_condition"];
						
						printf("%s\n", $review->book_isbn);
						printf("%s\n", $review->book_title);
						printf("%s\n", $review->book_condition);
						printf("%s\n", $review->buyer_name);
					}
				}
			}
		}
	}
	
	public function getBook($book, $display_name) {
		$query = "SELECT * FROM books WHERE isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
		if ($result = $this->connection->query($query)) {
			$row = $result->fetch_assoc();
			foreach($row as $value) {
				printf("%s\n",$value);
			}
			$history = "INSERT INTO browsehistory (display_name, isbn, title, author, year, publisher, genre, seller_name) VALUES ('".$display_name
				."', '".$row["isbn"]
				."', '".$row["title"]
				."', '".$row["author"]
				."', '".$row["year"]
				."', '".$row["publisher"]
				."', '".$row["genre"]
				."', '".$row["seller_name"]
				."')";
			$this->connection->query($history);
			$comments = "SELECT book_comments FROM reviews WHERE book_isbn='".$book->isbn."' AND seller_name='".$book->seller_name."' AND book_condition='".$book->book_condition."'";
			if ($rs = $this->connection->query($comments)) {
				while ($row = $rs->fetch_assoc()) {
						printf("%s\n", $row["book_comments"]);
				}
			}
			printf("done");
		}
	}
	
	
	public function search($constraint)
	{
	
		$query = "SELECT * FROM books WHERE ";
		
		if ($constraint->isbn != "") {
			$query = $query."isbn='".$constraint->isbn."'";
		} else {
		
			if ($constraint->titleExact) {
				$query = $query."title='".$constraint->title."'";
			} else {
				$query = $query."title LIKE '%".$constraint->title."%'";
			}

			if ($constraint->author != "") {
				if ($constraint->authorExact) {
					$query = $query." AND author='".$constraint->author."'";
				} else {
					$query = $query." AND author LIKE '%".$constraint->author."%'";
				}
			}
			
			if ($constraint->publisher != "") {
				if ($constraint->publisherExact) {
					$query = $query." AND publisher='".$constraint->publisher."'";
				} else {
					$query = $query." AND publisher LIKE '%".$constraint->publisher."%'";
				}
			}
			
			if ($constraint->genre != "") {
				if ($constraint->genreExact) {
					$query = $query." AND genre='".$constraint->genre."'";
				} else {
					$query = $query." AND genre LIKE '%".$constraint->genre."%'";
				}
			}
			
			if ($constraint->seller_name != "") {
				if ($constraint->sellerExact) {
					$query = $query." AND seller_name='".$constraint->seller_name."'";
				} else {
					$query = $query." AND seller_name LIKE '%".$constraint->seller_name."%'";
				}
			}
			
			if ($constraint->minYear != 0) {
				$query = $query." AND year >= ".$constraint->minYear."'";
			}
			
			if ($constraint->maxYear != -1) {
				$query = $query." AND year <= ".$constraint->maxYear."'";
			}
			
			if ($constraint->minPrice != 0) {
				$query = $query." AND price >= ".$constraint->minPrice."'";
			}
			
			if ($constraint->maxPrice != -1) {
				$query = $query." AND price <= ".$constraint->maxPrice."'";
			}
			
			$query = $query." AND quantity > 0 AND (isbn, seller_name) NOT IN (SELECT isbn, seller_name FROM suspendedbooks where isbn = books.isbn)";
			
		}
		
		//$returnArray = array();
		
		if($res = $this->connection->query($query))
		{
			while($row = $res->fetch_assoc())
			{
				/*
				printf("%s\n", $row["isbn"]);
				printf("%s\n", $row["title"]);
				printf("%s\n", $row["author"]);
				printf("%s\n", $row["year"]);
				printf("%s\n", $row["publisher"]);
				printf("%s\n", $row["genre"]);
				printf("%s\n", $row["seller_name"]);
				printf("%s\n", $row["price"]);
				printf("%s\n", $row["quantity"]);
				printf("%s\n", $row["book_condition"]);
				*/
				
				foreach($row as $value)
					printf("%s\n",$value);
			}
		}
		
		//printf("%s",$query);
	
	}
	
	public function getTopBooks() {
		$query = "SELECT * FROM books WHERE (isbn, seller_name) NOT IN (SELECT isbn, seller_name FROM suspendedbooks where isbn = books.isbn) ORDER BY rating DESC";
		if($res = $this->connection->query($query))
		{
			for ($x=0; $x<3; $x++)
			{
				$row = $res->fetch_assoc();
				if ($row != null) {
				foreach($row as $value)
					printf("%s\n",$value);
				}
			}
		}	
	}
	
	public function getTopUsers() {
		$query = "SELECT display_name FROM users WHERE suspended <> 1 AND accepted='1' ORDER BY rating DESC";
		if($res = $this->connection->query($query))
		{
			for ($x=0; $x<3; $x++)
			{
					$row = $res->fetch_assoc();
					if ($row != null)
						printf("%s\n",$row["display_name"]);
			}
		}	
	}
	
	public function addReview($review) {
		$query = "SELECT book_stars FROM reviews WHERE book_isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."' AND buyer_name='".$review->buyer_name."' AND book_condition='".$review->book_condition."'";
		if($result = $this->connection->query($query)) {
			if($result->num_rows < 1) {
				$add = "INSERT INTO reviews (book_isbn, book_title, book_condition, seller_name, buyer_name) VALUES ('".$review->book_isbn
				."', '".$review->book_title
				."', '".$review->book_condition
				."', '".$review->seller_name
				."', '".$review->buyer_name
				."')";
				$this->connection->query($add);
			} else {
				if ($review->reviewed_by_buyer > 0) {
					$update = "UPDATE reviews SET reviewed_by_buyer='1', book_stars='".$review->book_stars."', book_comments='".$review->book_comments."', reviewed_by_buyer='".$review->reviewed_by_buyer."', seller_stars='".$review->seller_stars."', seller_comments='".$review->seller_comments
					."' WHERE book_isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."' AND buyer_name='".$review->buyer_name."' AND book_condition='".$review->book_condition."'";
					$this->connection->query($update);
					$this->updateBookRating($review);
					$this->updateSellerRating($review);
					if ($review->book_stars < 2)
						$this->addBookComplaint($review);
					printf("true");
				}
				else if ($review->reviewed_by_seller > 0) {
					$update = "UPDATE reviews SET reviewed_by_seller='1', buyer_stars='".$review->buyer_stars."', buyer_comments='".$review->buyer_comments
					."' WHERE book_isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."' AND buyer_name='".$review->buyer_name."' AND book_condition='".$review->book_condition."'";
					$this->connection->query($update);
					$this->updateBuyerRating($review);
					printf("true");
				}
			}
		}
	}
	
	public function addBookComplaint($review) {
		$search = "SELECT buyer_name FROM complaints WHERE book_isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."' AND buyer_name='".$review->buyer_name."'";
		if($result = $this->connection->query($search))
		{
			if ($result->num_rows > 0)
				return;
			else {
				$add = "INSERT INTO complaints (book_isbn, seller_name, buyer_name) VALUES ('".$review->book_isbn
				."', '".$review->seller_name
				."', '".$review->buyer_name
				."')";
				$this->connection->query($add);
			}
		}
		$check = "SELECT buyer_name FROM complaints WHERE book_isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."'";
		if($result = $this->connection->query($check)) {
			if ($result->num_rows > 2) {
				$suspend = "INSERT INTO suspendedbooks (isbn, seller_name) VALUES ('".$review->book_isbn
				."', '".$review->seller_name
				."')";
				$this->connection->query($suspend);
				$warning = "UPDATE users SET warnings = warnings + 1 WHERE display_name='".$review->seller_name."'";
				if ($this->connection->query($warning)) {
					$checkuser = "SELECT warnings FROM users WHERE display_name='".$review->seller_name."'";
					if ($rs = $this->connection->query($checkuser)) {
						$row = $rs->fetch_assoc();
						if ($row["warnings"] > 2) {
							$suspenduser = "UPDATE users SET suspended='1' WHERE display_name='".$review->seller_name."'";
							$this->connection->query($suspenduser);
						}
					}
				}
			}
		}
	}
	
	public function updateBookRating($review) {
		$query = "UPDATE books SET rating = ";
		if ($review->book_stars == 1)
			$query = $query." rating - 2";
		elseif  ($review->book_stars == 2)
			$query = $query." rating - 1";
		elseif  ($review->book_stars == 3)
			return;
		elseif  ($review->book_stars == 4)
			$query = $query." rating + 1";
		elseif  ($review->book_stars == 5)
			$query = $query." rating + 2";
		$query = $query." WHERE isbn='".$review->book_isbn."' AND seller_name='".$review->seller_name."' AND book_condition='".$review->book_condition."'";
		$this->connection->query($query);
	}
	
	public function updateSellerRating($review) {
		$query = "UPDATE users SET rating = ";
		if ($review->seller_stars == 1)
			$query = $query." rating - 2";
		elseif  ($review->seller_stars == 2)
			$query = $query." rating - 1";
		elseif  ($review->seller_stars == 3)
			return;
		elseif  ($review->seller_stars == 4)
			$query = $query." rating + 1";
		elseif  ($review->seller_stars == 5)
			$query = $query." rating + 2";
		$query = $query." WHERE display_name='".$review->seller_name."'";
		$this->connection->query($query);
	}
	
	public function updateBuyerRating($review) {
		$query = "UPDATE users SET rating = ";
		if ($review->buyer_stars == 1)
			$query = $query." rating - 2";
		elseif  ($review->buyer_stars == 2)
			$query = $query." rating - 1";
		elseif  ($review->buyer_stars == 3)
			return;
		elseif  ($review->buyer_stars == 4)
			$query = $query." rating + 1";
		elseif  ($review->buyer_stars == 5)
			$query = $query." rating + 2";
		$query = $query." WHERE display_name='".$review->buyer_name."'";
		$this->connection->query($query);
	}
	
public function randString($length, $charset='ABCDEFGHJKLMNOPQRSTUVWXYZabcdefghijkmnopqrstuvwxyz0123456789')
{
    $str = '';
    $count = strlen($charset);
    while ($length--) {
        $str .= $charset[mt_rand(0, $count-1)];
    }
    return $str;
}

public function register($user)
{
	$query = "SELECT warnings FROM users WHERE login_name='".$user->login_name."'";
	if($result = $this->connection->query($query)) {
		if($result->num_rows > 0) {
			printf("1");
			return;
		}
	}
	$query = "SELECT warnings FROM users WHERE display_name='".$user->display_name."'";
	if($result = $this->connection->query($query)) {
		if($result->num_rows > 0) {
			printf("2");
			return;
		}
	}
	
	$password = $this->randString(8);
	$user->password_hash = sha1($password);
	
	$query = "INSERT INTO users (login_name, password_hash, display_name, warnings, credits, suspended, rating) VALUES ('".$user->login_name
				."', '".$user->password_hash
				."', '".$user->display_name
				."', '".$user->warnings
				."', '".$user->credits
				."', '".$user->suspended
				."', '".$user->rating
				."')";
	if ($this->connection->query($query)) {
		printf("%s", $password);
	}
}

public function login($user)
{
	$user->password_hash = sha1($user->password_hash);
	$query = "SELECT display_name, warnings, credits, suspended, rating, changedpass, admin FROM users WHERE login_name='".$user->login_name."' AND password_hash='".$user->password_hash."' AND accepted='1'";
	if($result = $this->connection->query($query)) {
		if($result->num_rows < 1) {
			printf("false");
		} else {
			$row = $result->fetch_assoc();
			printf("%s\n", $row["display_name"]);
			printf("%s\n", $row["warnings"]);
			printf("%s\n", $row["credits"]);
			printf("%s\n", $row["suspended"]);
			printf("%s\n", $row["rating"]);
			printf("%s\n", $row["changedpass"]);
			printf("%s\n", $row["admin"]);
		}
	}
}

public function loginAdmin($user)
{
	$user->password_hash = sha1($user->password_hash);
	$query = "SELECT login_name FROM admins WHERE login_name='".$user->login_name."' AND password_hash='".$user->password_hash."'";
	if($result = $this->connection->query($query)) {
		if($result->num_rows < 1) {
			printf("false");
		} else {
			printf("true");
		}
	}
}

public function getNewUsers()
{
	$query = "SELECT display_name FROM users WHERE accepted='0'";
	if($res = $this->connection->query($query)) {
			while($row = $res->fetch_assoc()) {
				printf("%s\n",$row["display_name"]);
			}
	}
}

public function acceptUser($display_name) {
	$query = "UPDATE users SET accepted='1' WHERE display_name='".$display_name."'";
	if($res = $this->connection->query($query))
		printf("true");
	else
		printf("false");
}

public function declineUser($display_name) {
	$query = "DELETE FROM users WHERE display_name='".$display_name."'";
	if($res = $this->connection->query($query))
		printf("true");
	else
		printf("false");
}

public function changepass($c, $n)
{
	$c->password_hash = sha1($c->password_hash);
	$query = "SELECT warnings FROM users WHERE display_name='".$c->display_name."' AND password_hash='".$c->password_hash."'";
	if($result = $this->connection->query($query)) {
		if($result->num_rows < 1) {
			printf("false");
		} else {
			$n->password_hash = sha1($n->password_hash);
			$change = "UPDATE users SET changedpass='1', password_hash='".$n->password_hash."' WHERE display_name='".$c->display_name."'";
				if($result = $this->connection->query($change)) {
					printf("true");
				}
		}
	} 
}

}

?>