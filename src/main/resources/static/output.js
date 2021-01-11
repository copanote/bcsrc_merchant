function test () {
  axios.post('https://isrnd.bccard.com:56443/src/http/tspTest', {
    firstName: 'Fred',
    lastName: 'Flintstone'
  })
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
}

function test2 () {
  axios.get('https://isrnd.bccard.com:56443/src//otp/request')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log("test:::" + error);
  });
}


function test3 () {
  axios.get('https://jsonplaceholder.typicode.com/todos/1')
  .then(function (response) {
    console.log(response);
  })
  .catch(function (error) {
    console.log(error);
  });
}