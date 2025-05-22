tööjuhend, enne esitamist eemaldada:
--------------------------------
projekti põhjalik kirjeldus, kus on kirjas programmi eesmärk ja selgitus programmi üldisest tööst, vajadusel lühike kasutusjuhis;
iga klassi kohta eraldi selle eesmärk ja olulisemad meetodid;
projekti tegemise protsessi kirjeldus (erinevad etapid ja rühmaliikmete osalemine neis);
iga rühmaliikme panus (sh tehtud klassid/meetodid) ja ajakulu (orienteeruvalt);
tegemise mured (nt millistest teadmistest/oskustest tundsite projekti tegemisel puudust);
hinnang oma töö lõpptulemusele (millega saite hästi hakkama ja mis vajab arendamist);
selgitus ja/või näited, kuidas programmi osi eraldi ja programmi tervikuna testisite ehk kuidas veendusite, et programm töötab korrektselt.
--------------------------------

AUTORID: Angelika Glaser ja Hedvig Annast

Programmi kirjeldus:
Programm kujutab endast suhtepäevikut. See võimaldab salvestada suhtes olulisi sündmusi ja salvestada ning genereerida
ideid edasisteks koosviibimisteks. Programmi kasutamiseks tuleb kompileerida ja käivitada vana.Main.java fail ja sisestada käsureale
soovitud andmed.

Klassid:
vana.Main - programmi vana peaklass, mille käivitamisel on võimalik programmi kasutada. Küsib kasutaja sisendit.
MainFX - JavaFX fail, mis loob visuaali ning sealt saab nuppudega liikuda eri screenidele.
Entry - abstraktne klass, kus on abstraktne meetod faili kirjutamiseks, meetodid failist lugemiseks, kindlate sissekannete leidmiseks ja getterid-setterid
ChangeEntry - Entry alamklass, mõeldud sissekannete muutmiseks
NewEntry - Entry alamklass, mõeldud uute sissekannete loomiseks
model.Idea - model.Idea klass on selleks, et luua idee objekt ning seal hoian ka kõik getterid ja setterid, mis on ideega seotud.
model.DateIdeaGenerator - See klass laiendab model.Idea klassi ning on eraldi loodud date/kohtingu tüüpi ideede jaosk.
model.HangoutIdeaGenerator - See klass laiendab model.Idea klassi ning on eraldi loodud hangout/kokkusaamise tüüpi ideede jaosk.
model.IdeaGenerator - see klass vastutab kõikide ideede eest, seal asuvad kõik funktsionaalsused, mis on eri ideede tegevustega seotud (lisamine, eemaldamine, muutmine jne).
Sinna on kogutud ka osad funktsionaalsused, mis tegelevad kasutajaga suhtlemisega, et hoida main fail puhtamana.
Tulevikus tahan seda natuke paremini jaotada.
getInputWithBackOption(String prompt) on hiljem lisatud funktsioon, mida ma ei saanud päris nii tööle nagu ma tahtsin, aga ta aitab natuke vältida koodi kordamist.

Rühmaliikmete panus:
Hedvig - lõi klassid Entry, ChangeEntry, NewEntry ja lõi Maini põhja, ligikaudu (mingi arv) tundi tööd
Angelika - pakkus projekti idee, lõi klassid model.Idea, model.IdeaGenerator, model.DateIdeaGenerator, model.HangoutIdeaGenerator, täiendas vana.Main klassi.
Angelika tegi JavaFX osa.

Murekohad:
Hedvig - Ei tundnud väga, et oleks teadmistest puudu jäänud - olen kursuselt piisavalt õppinud, et sain programmis
enamike meetodite loomisega oma teadmiste peal hakkama.
Angelika - Sain ka oma osaga päris hästi hakkama, ainuke murekoht oli, idee osas, tagasimineku lisamisega, kuna eri valikute variandid sain kasutaja vastuseid erinevalt,
 edaspidi tahaks muuta nii, et oleks võimalik ühtlustada tagasimineku funktsionaalsust. Aega kulus ca 10h.
Keeruline oli JavaFX/iga kõik tööle saada.

Hinnang oma töö tulemusele:
Hedvig - Mina olen programmiga väga rahul. Kõik ideed, mis alguses kokku leppisime, said projektile lisatud.
Angelika - Olen rahul, kõik toimib nii nagu soovisime ning sain oma osa täpselt nii detailseks kui plaanisin.
Olen ka JavaFX osaga rahul, tuli päris äge.

Programmi testimine:
Hedvig - testisin enda klasse Mainis siis, kui põhiprogramm polnud veel kirjutatud. Kui olin veendunud, et funktsioonid
töötavad nii, kuidas pidid, kirjutasin algse põhiprogrammi.
Angelika - Kuna ma plaanisin välja kõik põhifunktsionaalsused, siis tegin algselt üle-üldsise main faili osa ideedele, ning iga funktsionaalsuse valmimise järgselt täiendasin seda ja testisin,
et kõik toimiks nii nagu peab. Algselt ei olnud tagasiminekut plaanis, kui nägin töö valmimisel, et seda on vaja ning lisasin selle juurde, kui see osutus küllaltki keeruliseks, kuna üritasin koodi hoida puhtana.
Kui kõik funktsionaalsused olid valmis, siis testisin käsitsi kõiki variante ja tegin muudatuse vastavalt vajadusele.
