var month_olympic = [31,29,31,30,31,30,31,31,30,31,30,31];
var month_normal = [31,28,31,30,31,30,31,31,30,31,30,31];
var month_name = ["January","Febrary","March","April","May","June","July","Auguest","September","October","November","December"];

var holder = document.getElementById("days");
var prev = document.getElementById("prev");
var next = document.getElementById("next");
var ctitle = document.getElementById("calender-title");
var cyear = document.getElementById("calender-year");

var my_date = new Date();
var my_year = my_date.getFullYear();
var my_month = my_date.getMonth();
var my_day = my_date.getDate();

function prev(e) {
  e.preventDefault();
  my_month--;
  if (my_month < 0) {
    my_year--;
    my_month = 11;
  }
  refreshDate();
}

function next(e) {
  e.preventDefault();
  my_month++;
  if (my_month > 11) {
    my_year++;
    my_month = 0;
  }
  refreshDate();
}

// 刷新页面
function refreshDate() {
  var str = "";
  var totalDays = daysMonth(my_month, my_year);
  var firstDay = dayStart(my_month, my_year);
  var myclass;

  // 计算起始日之前的空白节点
  for (var i = 1; i < firstDay; i++) {
    str += "<li></li>";
  }

  for (var i = 0; i <= totalDays; i++) {
    if ((i < my_day && my_year == my_date.getFullYear() && my_month == my_date.getMonth()) // 小于今天的日子灰色显示
      || (my_year < my_date.getFullYear())
      || (my_year == my_date.getFullYear() && my_month < my_date.getMonth())) {
      myclass = " class='lightgrey'";
    } else if (i == my_day && my_year == my_date.getFullYear() && my_date.getMonth()) {
      myclass = " class='green greenbox'"; // 今天的日子绿框显示
    } else {
      myclass = " class='darkgrey'"; // 大于今天的日子深灰显示
    }
    str += "<li" + myclass + ">" + i + "</li>";
  }
  holder.innerHTML = str;
  ctitle.innerHTML = month_name[my_month];
  cyear.innerHTML = my_year;
}

// 获取某年某月第一天是星期几
function firstDay(month, year) {
  var tmpDate = new Date(year, month, 1);
  return (tmpDate.getDay());
}

// 计算某年是不是闰年
function daysMonth(month, year) {
  var tmp = year % 4;
  if (tmp == 0) {
    return (month_olympic[month]);
  } else {
    return (month_normal[month]);
  }
}

// 获取某年某月第一天是星期几
function dayStart(month, year) {
  var tmpDate = new Date(year, month, 1);
  return (tmpDate.getDay());
}

// 计算某年是不是闰年
function daysMonth(month, year) {
  var tmp = year % 4;
  if (tmp == 0) {
    return (month_olympic[month]);
  } else {
    return (month_normal[month]);
  }
}

refreshDate();
