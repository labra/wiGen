for obs in 1 5 10 50 100 500 1000
do
 for orgs in 1 5 10 50 100 500 1000
 do
  for datasets in 1 5 10 50 100 500 1000
  do
   for countries in 1 5 10 50 100 500 1000
   do
    ./wigen -c $countries -g $orgs --dataSets $datasets -o $obs --shex --shacl --time --single >> out2 
   done
  done
 done
done