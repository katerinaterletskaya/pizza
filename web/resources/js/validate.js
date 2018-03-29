function validate( )
{
    var check = document.getElementsByName("check");
    var valid = false;
    var j = 0;
    while (!valid && j < check.length) {
        if (check[j].checked)
            valid = true;
        j++;
    }
    if (!valid) {
        alert("Please select your desired items!");
        valid = false;
    }
    else{
        alert("Your order is successfully formed!")
    }
    return valid;
}
