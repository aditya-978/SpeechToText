import speech_recognition as sr
import socket

def recognize_speech():
    recognizer = sr.Recognizer()
    with sr.Microphone() as source:
        print("Listening...")
        recognizer.adjust_for_ambient_noise(source)
        audio = recognizer.listen(source)
    
    try:
        text = recognizer.recognize_google(audio)  # Using Google's Speech-to-Text API
        print(f"Recognized: {text}")
        return text
    except sr.UnknownValueError:
        return "Could not understand audio"
    except sr.RequestError:
        return "API unavailable"

def send_text_to_java(text):
    host = "127.0.0.1"  # Localhost
    port = 5000  # Port for socket communication

    with socket.socket(socket.AF_INET, socket.SOCK_STREAM) as client_socket:
        client_socket.connect((host, port))
        client_socket.sendall(text.encode())

if __name__ == "__main__":
    while True:
        speech_text = recognize_speech()
        send_text_to_java(speech_text)
