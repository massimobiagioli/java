#include <LiquidCrystal.h>

LiquidCrystal lcd(12, 11, 5, 4, 3, 2);

const int contrastPin = 9;
char ch;
String buffer = "";
String command;
String value;

void setup() {
  pinMode(contrastPin, OUTPUT);
  analogWrite(contrastPin, 88);
  
  lcd.begin(16, 2);
  lcd.print("Ready");
  
  Serial.begin(9600);
}

void loop() {
  while (Serial.available() > 0) {
    ch = Serial.read();
    if ('\n' != ch) {
      buffer.concat(String(ch));
    } else {
      processBuffer();
    }
  }
}

void processBuffer() {
  lcd.clear();
  displayMessage();
  parseCommand();
  buffer = "";
}

void displayMessage() {
  lcd.setCursor(0, 0);
  lcd.print(buffer);
}

void displayCommand() {
  lcd.setCursor(0, 1);
  lcd.print(command);
  if (value.length() > 0) {
    lcd.print(" -> ");
    lcd.print(value);
  }
}

void parseCommand() {
  command = "";
  value = "";
  
  int i = buffer.indexOf(":");
  if (-1 != i) {
    command = buffer.substring(0, i);
    value = buffer.substring(i + 1);  
  } else {
    command = buffer;
  }
  
  displayCommand();
}
