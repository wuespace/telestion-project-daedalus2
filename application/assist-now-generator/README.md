# Assist Now Data Generator

Generate an access token on this website or ask someone with a token:
https://www.u-blox.com/en/assistnow-service-evaluation-token-request-form

and put the received token in the `token.txt`.

Next, call the script with:

```sh
python main.py
```

The script downloads and packs the A-GPS data in a binary format with the name `assist-now-binary-*`
and a Telestion compatible format with the name `assist-now-telestion-*`.

The Telestion compatible format can then be uploaded via the A-GPS Upload widget in the Project client.
