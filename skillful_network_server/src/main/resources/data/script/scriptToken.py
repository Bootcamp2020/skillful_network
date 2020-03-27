'''
VOIR https://pyjwt.readthedocs.io/en/latest/
pip install pyjwt

python ./scriptToken.py encrypt 12 son.email@gmail.com xxxxx 
    avec argument n°1 = encrypt, argument n°2 = 12 (id), (email), (password)
python ./scriptToken.py decrypt eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp.....
    avec argument n°1 = decrypt, argument n°2 = token

legnede des erreurs de retour:
-1 => pas d argument n°1
-2 => encrypt ou decrypt ???
-3 => pas de token
-4 => token invalide
-5 => temps de validite du token expire
-6 => id ou email ou password manquant(s)
'''

import jwt
import sys
from datetime import datetime, timedelta
import json

key = 'aPa53eU6N' #cle au choix

answer ='' #retour du script python

try:
    sys.argv[1] 
    # si argument n°1 = encrypt ou decrypt
    if sys.argv[1] == 'encrypt':
        try:
            message = {
                'id': sys.argv[2],
                'email': sys.argv[3],
                'password': sys.argv[4],
                'exp': datetime.utcnow()+timedelta(hours=3) # duree du token 3 heures
            }
            token = jwt.encode(message, key, algorithm='HS384')
            answer = token
        except:
            answer = -6    
    elif sys.argv[1] == 'decrypt':
        try:
            sys.argv[2] # egal token
            try:
                decoded =jwt.decode(sys.argv[2], key, algorithm='HS384')
                answer = decoded
            except jwt.ExpiredSignatureError:
                answer = -5
            except:
                answer = -4
        except IndexError:
            answer = -3
    else:
        answer = -2
except IndexError:
    answer = -1

if type(answer) == int: # erreur / probleme
    print(answer)
elif type(answer) == bytes: # token sans b
    answerSansB = answer.decode('utf8')
    print(answerSansB)
elif type(answer) == dict: # dictionnaire sans exp et en JSON
    del answer['exp']
    print(json.dumps(answer))

