
until $(curl --output /dev/null http://localhost:8080/statusa); do
  echo 'waiting for api...'
  sleep 5
done


echo "DONEEEEE"

