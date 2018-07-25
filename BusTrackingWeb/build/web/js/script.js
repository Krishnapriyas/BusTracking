/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/*

var num = 10;
function sayHai(num1, num2) {
    var num = 100;
    alert("Hai : " + (num1 + num2));
}
//sayHai(20,30); // called a function
// object cration 

//alert(car.name); // displays name
//alert(car.speed);// displays speed
//alert(car.showCar("harris")); // calles function and passes parameter to the function
*/
 /*
(function() {
    var num = 100;
    alert("Hai : ");
})(); // ANONYMOUS IMMEDIATE IMPLISIT FUNCTION CALLS
 */

function change(){
 //   alert(item.innerHTML);
 //  item.innerHTML = "<b>hellope </b>";
   document.getElementById("name").value = "jude";
}

function  changeValue(item){
    var x = 10;
   
    console.log(x);
    var y = 30;
    console.log(y);
   document.getElementById("aaa").innerHTML  = item.value;
   
}

class Rectangle {
  constructor(height, width) {
    this.height = height;
    this.width = width;
  }
  // Getter
  get area() {
    return this.calcArea();
  }
  // Method
  calcArea() {
    return this.height * this.width;
  }
}
 debugger;
var rect = new Rectangle(10,20);

rect.sayHai = function (){
    alert("thsi ");
}
 alert(rect.calcArea());
 rect.sayHai();

 
 
  
  