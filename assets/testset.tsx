<?xml version="1.0" encoding="UTF-8"?>
<tileset name="testset" tilewidth="128" tileheight="128" tilecount="21" columns="0">
 <grid orientation="orthogonal" width="1" height="1"/>
 <terraintypes>
  <terrain name="Новый участок" tile="13"/>
 </terraintypes>
 <tile id="9">
  <image width="128" height="128" source="glass.png"/>
 </tile>
 <tile id="10">
  <image width="128" height="128" source="road1.png"/>
 </tile>
 <tile id="11" terrain=",,,0">
  <image width="128" height="128" source="road2.png"/>
 </tile>
 <tile id="12">
  <image width="128" height="128" source="road3.png"/>
 </tile>
 <tile id="13">
  <image width="128" height="128" source="road4.png"/>
 </tile>
 <tile id="14">
  <image width="128" height="128" source="road5.png"/>
 </tile>
 <tile id="15">
  <image width="128" height="128" source="road6.png"/>
 </tile>
 <tile id="16">
  <image width="128" height="128" source="road7.png"/>
 </tile>
 <tile id="17">
  <image width="128" height="128" source="road8.png"/>
 </tile>
 <tile id="18">
  <image width="128" height="128" source="stoun.png"/>
 </tile>
 <tile id="19">
  <image width="128" height="128" source="wall1.png"/>
  <objectgroup draworder="index">
   <object id="1" x="8" y="-2" width="106" height="132"/>
  </objectgroup>
 </tile>
 <tile id="20">
  <image width="128" height="128" source="wall2.png"/>
  <objectgroup draworder="index">
   <object id="1" x="14" y="19" width="99" height="107"/>
  </objectgroup>
 </tile>
 <tile id="21" terrain=",,,0">
  <image width="128" height="128" source="wall3.png"/>
  <objectgroup draworder="index">
   <object id="1" x="16" y="17" width="122" height="110"/>
  </objectgroup>
 </tile>
 <tile id="22" terrain="0,0,0,0">
  <image width="128" height="128" source="wall4.png"/>
  <objectgroup draworder="index">
   <object id="1" x="0" y="12" width="138" height="107"/>
  </objectgroup>
 </tile>
 <tile id="23" terrain="0,,0,">
  <image width="128" height="128" source="wall5.png"/>
  <objectgroup draworder="index">
   <object id="1" x="-2" y="15" width="111" height="100"/>
  </objectgroup>
 </tile>
 <tile id="24">
  <image width="128" height="128" source="wall6.png"/>
  <objectgroup draworder="index">
   <object id="1" x="15" y="1" width="98" height="106"/>
  </objectgroup>
 </tile>
 <tile id="25" terrain=",0,,0">
  <image width="128" height="128" source="wall7.png"/>
  <objectgroup draworder="index">
   <object id="1" x="19" y="14" width="106" height="99"/>
  </objectgroup>
 </tile>
 <tile id="26">
  <image width="128" height="128" source="water1.png"/>
 </tile>
 <tile id="27">
  <image width="128" height="128" source="water1a2.png"/>
 </tile>
 <tile id="28">
  <image width="128" height="128" source="water1a3.png"/>
 </tile>
 <tile id="29">
  <image width="128" height="128" source="water1a4.png"/>
  <animation>
   <frame tileid="26" duration="100"/>
   <frame tileid="27" duration="100"/>
   <frame tileid="28" duration="100"/>
   <frame tileid="29" duration="100"/>
  </animation>
 </tile>
 <wangsets>
  <wangset name="New Wang Set" tile="-1">
   <wangcornercolor name="" color="#ff0000" tile="-1" probability="1"/>
   <wangcornercolor name="" color="#00ff00" tile="-1" probability="1"/>
   <wangtile tileid="12" wangid="0x20202020"/>
   <wangtile tileid="21" wangid="0x10102010"/>
  </wangset>
 </wangsets>
</tileset>
