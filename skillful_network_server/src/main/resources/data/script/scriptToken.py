'''
VOIR https://pyjwt.readthedocs.io/en/latest/
pip install pyjwt

python ./monScriptToken.py encrypt 12 son.email@gmail.com sonPrenom sonNom 
    avec argument n°1 = encrypt, argument n°2 = 12 (id), etc...
python ./monScriptToken.py decrypt eyJhbGciOiJIUzI1NiIsInR5cCI6Ikp.....
    avec argument n°1 = decrypt, argument n°2 = token

legnede des erreurs de retour:
-1 => pas d argument n°1
-2 => encrypt ou decrypt ???
-3 => pas de token
-4 => token invalide
-5 => temps de validite du token expire
'''

import jwt
import sys
from datetime import datetime, timedelta

key = 'aPa53eU6N' #cle au choix

answer ='' #retour du script python

try:
    sys.argv[1] 

    # si argument n°1 = encrypt ou decrypt
    if sys.argv[1] == 'encrypt':
        # on cree le code en fonction des arguments
        code = ''
        for valeur in sys.argv[2:]:
            code += str(valeur)+' '
        message = {
            'name': code,
            'exp': datetime.utcnow()+timedelta(seconds=60) # duree du token 60 secondes
        }
        token = jwt.encode(message, key, algorithm='HS384')
        answer = token    
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

print(answer)


