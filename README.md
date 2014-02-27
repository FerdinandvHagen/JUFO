Schnelleinstieg:
===============
Es wird nur die Leinwand.jar benötigt! Die .jar wird als Library in der gwünschten IDE hinzugefügt.
Klassen die auf die Leinwand zugreifen wollen, müssen diese zunächst durch

import org.Leinwand.*;

importieren. Danach ist sie ohne weitere Konfiguration verwendbar.


Beispielporjekt:
Findet sich im Ordner src/org/Test
Wird die Leinwand.jar direkt gestartet (z.B. Doppelklick), wird diese Demo ausgeführt.


Dokumentation:
===============
Javadoc befindet sich in doc.
Am Besten alle Dateien herunterladen (z.B. als ZIP-Datei), dann kann man durch anklicken der index.html schön durch die Dateien navigieren



Kurzfassung
===============
Für die Visualisierung von Objekten beziehungsweise dem spielerischen Erlernen von Objektorientierung im Informatik-Unterricht werden verschiedene vorgefertigte Hilfen verwendet. Häufig verwendet werden dabei Klassen, die auf fertige Java-Bausteine aufbauen. Damit ist es möglich in Fenstern auf dem Monitor einfache geometrische Figuren darzustellen (häufig und auch hier einfach nur "Leinwand" gennant, ). Die vielleicht beste und bekannte Alternative ist die "engineAlpha" von Michael Andonie. Auch sie basiert auf fertigen Bausteinen von Java.

Leider haben alle diese Projekte einer mehr oder weniger schlechte Performance gemeinsam. Michael Adonie hat es trotzdem geschafft, einigermaßen gute Ergebnisse zu erzielen. Auffällig lang sind jedoch die Ladezeiten und die engineAlpha erfordert eine komplett neue Einarbeitung, da die Syntax abweichend von anderen Leinwänden ist. Dafür bietet sie viele Funktionen, die die Programmierung von Spielen erleichtert.

Die Vorteile dieser beiden Projekte sollten in diesem Programm verbunden werden. Der Funktionsumfang ist natürlich deutlich geringer als bei engineAlpha, hat aber trotzdem alle notwendigen Elemente an Bord. Gleichzeitig bietet die Graphikkartenunterstützung wie sie hier realisiert wurde eine unerreichbare Performance.

Der Graphikkartenzugriff wurde realisiert mit der Leightweight Java Game Development Library (LWJGL) - einem Java-Wrapper für die openGL-Umgebung sowie deren Ausprägungen. Damit erhält man Zugriff auf alle Geräte im Computer, die eine openGL, openAL oder openCL Schnittstelle unterstützen. Zur Zeit wird jedoch nur die openGL-Komponente genutzt. LWJGL und damit auch dieses Projekt ist universell auf allen Plattformen (OSX, Linux, Windows, etc.) einsetzbar.

Das vorliegende Projekt konnte auch schon bei einem größeren Schulprojekt eingesetzt werden und stellte dabei seine Leistungsfähigkeit und einfache Bedienbarkeit unter Beweis.

