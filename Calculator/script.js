let display = document.getElementById("display");

function appendValue(value) {
  if (display.innerText === "0") {
    display.innerText = value;
  } else {
    display.innerText += value;
  }
}

function clearDisplay() {
  display.innerText = "0";
}

function deleteLast() {
  let text = display.innerText;
  if (text.length === 1) {
    display.innerText = "0";
  } else {
    display.innerText = text.slice(0, -1);
  }
}

function calculateResult() {
  try {
    let expression = display.innerText.replace(/÷/g, "/").replace(/×/g, "*");
    let result = eval(expression);
    display.innerText = result;
  } catch {
    display.innerText = "Error";
  }
}
