# [Silver I] Mrlja - 25162 

[문제 링크](https://www.acmicpc.net/problem/25162) 

### 성능 요약

메모리: 14116 KB, 시간: 108 ms

### 분류

브루트포스 알고리즘, 구현, 문자열

### 제출 일자

2024년 6월 20일 06:53:02

### 문제 설명

<p>Mirko je po svojoj bilježnici prolio kakao i tako je nasred prednje stranice (naslovnice) nastala mrlja. Mirko će mrlju prekriti naljepnicom. Da problem bude veći, i mrlja i naljepnica neobičnih su oblika pa Mirko možda neće uspjeti prekriti cijelu mrlju.</p>

<p>Mrlju opisujemo kao skup polja označenih znakovima # unutar zamišljene tablice od N x N polja koja predstavlja sredinu stranice. Naljepnicu opisujemo na isti način, kao skup polja označenih znakovima # unutar zamišljene tablice istih dimenzija. Pogledaj donje primjere za bolje razumijevanje.</p>

<p>Mirko može:</p>

<ol>
	<li>rotirati naljepnicu (za višekratnike od 90°), bez preokretanja;</li>
	<li>pomaknuti naljepnicu u bilo kojem smjeru tako da što bolje prekrije mrlju.</li>
</ol>

<p>Mogućnost 1 znači da Mirko može naljepnicu postaviti u četiri različita oblika s obzirom na stranicu. Mogućnost 2 znači da Mirkova naljepnica može izaći i izvan tablice koja opisuje mrlju.</p>

<p>Pomozi Mirku i napiši program koji odgovara na sljedeće pitanje: koliki je najmanji broj dijelova mrlje koje će ostati vidljive ispod naljepnice nakon pokrivanja? (Ako je mrlju moguće potpuno prekriti, odgovor je nula.)</p>

<p style="text-align: center;"><img alt="" src="https://upload.acmicpc.net/ec50d876-af7f-4625-9386-2af0ea721740/-/preview/" style="width: 532px; height: 130px;"></p>

<p style="text-align: center;">Slika opisuje treći primjer niže. Lijevo je mrlja, a u sredini naljepnica, koju valja rotirati udesno (za 90° u smjeru kazaljke sata) i potom pomaknuti tako da prekrije sve osim jednog (gornjeg desnog) dijela mrlje, kao na desnom dijelu slike.</p>

### 입력 

 <p>U prvom je retku broj N (1 ≤ N ≤ 10) iz teksta zadatka, dimenzija zamišljene tablice.</p>

<p>Sljedećih N redaka sadrži po N znakova (bez razmaka) koji opisuju mrlju. Znakovi ljestve ("#") predstavljaju mrlju, a ostatak znakova su točke (".").</p>

<p>Slijedi prazan redak, a nakon njega N redaka od po N znakova koji na isti način opisuju naljepnicu.</p>

<p>(Polja koja predstavljaju mrlju bit će povezana, tj. mrlja se neće sastojati od odvojenih dijelova. Isto vrijedi za naljepnicu.)</p>

### 출력 

 <p>U prvi i jedini redak ispiši traženi broj iz teksta zadatka.</p>

