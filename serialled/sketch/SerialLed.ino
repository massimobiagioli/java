int greenLedPin = 13;
int yellowLedPin = 12;
int redLedPin = 11;
int blueLedPin = 10;

int greenLedValue;
int yellowLedValue;
int redLedValue;
int blueLedValue;

void setup() {
  Serial.begin(9600);
  pinMode(greenLedPin, OUTPUT);
  pinMode(yellowLedPin, OUTPUT);
  pinMode(redLedPin, OUTPUT);
  pinMode(blueLedPin, OUTPUT);
}

void loop() {
  
  while (Serial.available() > 0) {    
    greenLedValue = Serial.parseInt();
    yellowLedValue = Serial.parseInt();
    redLedValue = Serial.parseInt();
    blueLedValue = Serial.parseInt();    
  }
  
  digitalWrite(greenLedPin, greenLedValue);
  digitalWrite(yellowLedPin, yellowLedValue);
  digitalWrite(redLedPin, redLedValue);
  digitalWrite(blueLedPin, blueLedValue);  
}
