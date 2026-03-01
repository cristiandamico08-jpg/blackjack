@echo on
rem imposta il percorso dove hai estratto l’SDK di JavaFX
set PATH_TO_FX="C:\Program Files (x86)\BlackJack\javafx-sdk-25.0.2\lib"

rem avvia l’applicazione passando module‑path e moduli necessari. Utilizza `javaw` al posto di `java` per evitare la finestra del prompt dei comandi
javaw --module-path %PATH_TO_FX% --add-modules javafx.controls,javafx.fxml -jar BlackJack.jar


pause