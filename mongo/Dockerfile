FROM mongo
COPY init.json /init.json
CMD mongoimport --host mongodb --db bikes --collection bikes --type json --file /init.json --jsonArray
