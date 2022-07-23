window.addEventListener(
  "DOMContentLoaded",
  function (e) {
    var stage = document.getElementById("stage");
    var fadeComplete = function (e) {
      stage.appendChild(arr[0]);
    };
    var arr = stage.getElementsByTagName("a");
    for (var i = 0; i < arr.length; i++) {
      arr[i].addEventListener("animationend", fadeComplete, false);
    }
  },
  false
);

function openForm() {
  document.getElementById("myForm").style.display = "block";
}

function closeForm() {
  document.getElementById("myForm").style.display = "none";
}

function fade_nav_bar_link(nav_bar_link) {
  var element = document.getElementById(nav_bar_link);
  element.classList.add("nav_bar_link_transparent");
}

function unfade_nav_bar_link(nav_bar_link) {
  var element = document.getElementById(nav_bar_link);
  element.classList.remove("nav_bar_link_transparent");
}
