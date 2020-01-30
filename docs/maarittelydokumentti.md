Harjoitustyössä toteutetaan säteenseuranta-algoritmi.

Työn tavoitteena on luoda ohjelma, joka pystyy tuottamaan kuvia yksinkertaisista 3D-kappaleista (esim. pallosta).
Valitsin ray tracing algoritmin, koska se on ymmärtääkseni helpoimmin toteutettava 3D-renderöintiin soveltuva algoritmi.
Ohjelmassa toteutetaan ns. rekursiivinen säteenseuranta-algoritmi, joka mahdollistaa säteiden heijastumisen kappaleista, ja
tällä tavoin pystyy tuottamaan realistisempia kuvia, kuin esim. säteensuuntaus-algoritmiin perustuva ohjelma. Osana ohjelmaa
toteutetaan useita luokkia, kuten pallo-luokka, jonka avulla voidaan tutkia säteiden osumia kuvassa esiintyviin palloihin. Myös
eri materiaaleille tullaan luultavasti kehittämään omat luokat, jotka määrittelevät kuinka ne vuorovaikuttavat säteiden kanssa.
Mitään varsinaisia tietorakenteita ei luultavasti tulla toteuttamaan, jos ei esim. vektoria lasketa. Rekursiivisen säteenseuranta-
algoritmin aikavaativuutta on vaikea ainakin näin etukäteen määritellä, enkä siksi osaa asettaa mitään järkevää tavoitetta sen
suhteen. Ohjelma ei ota syötteitä, ja se tulostaa 'plain ppm' formaattia olevia kuvia, joita voi katsella useilla kuvankäsittely-ohjelmilla. Itselläni käytössä on GIMP.

Lähteet:
<https://www.scratchapixel.com/lessons/3d-basic-rendering/introduction-to-ray-tracing>
<http://netpbm.sourceforge.net/doc/ppm.html>
