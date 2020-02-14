# Materiaalit

Materiaalit määrittelevät kuinka säteet käyttäytyvät osuessaan pintaan. Tällä hetkellä ainoa toteutettu materiaali on ns. "Lambertian" 
-materiaali, joka heijastaa säteitä satunnaisiin suuntiin ja näyttää siten mattapinnalta. Satunnaisen suunnan valinta on toteutettu
valitsemalla satunnainen piste osumakohtaa tangentoivasta yksikköpallosta ja suuntaamalla uusi säde sen suuntaan osumakohdasta.

# Rekursiivinen sironta

Ohjelman sydämenä toimii rekursiivinen sironta-algoritmi, joka tarkastaa jokaisen säteen mahdollisen osuman johonkin objektiin.
Jos osuma löytyy, algoritmi laskee säteen uuden suunnan ja kutsuu itseään. Jos osumaa ei tule, palauttaa algoritmi pixelille
sijainnin perusteella taustavärin.

# Kamera -luokka

Näkökulma on toteutettu niin, että "katsojan silmä" on pisteessä (0,0,0) ja säteet lähtevät tästä pisteestä jokaiseen kuvan
pixeliin. Tätä varten on toteutettu Kamera -luokka, jossa on metodi säteiden suunnan laskemiselle.

# Antialiasing

Antialiasing on toteutettu lähettämällä useita säteitä jokaisen pixelin kohdalla sen ympäristöön. Tietyn pixelin väri saadaan
kaikkien näiden säteiden keskiarvona. Tällä tavoin voidaan välttää terävät reunat kuvassa.

