for obs in 1 2000 5000 10000 50000 100000
do
 for orgs in 10
 do
  for datasets in 10
  do
   for countries in 100
   do
    ./wigen -c $countries -g $orgs --dataSets $datasets -o $obs --shex --shacl --time --single >> out 
   done
  done
 done
done