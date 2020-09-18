//Potrebno napraviti database sa nazivom "ib"
INSERT INTO authority(authority_id,name) VALUES (1,"Administrator");
INSERT INTO authority(authority_id,name) VALUES (2,"Korisnik");

INSERT INTO user(user_id,active,certificate,email,password,authority_id) VALUES(0,true,"neki tamo sertifikat","a@a.com","97",1);