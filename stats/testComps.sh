values="1 5 10 50 100 500 1000" 
#wiGenRunner="\basura\wigen-0.0.1\wigen-0.0.1\bin\wigen.bat"
wiGenRunner=./wigen

for value in $values
do
outName="outCompsNR"
params="--countries 1 --dataSets 1 --slices 1 --observations 1 --computations $value --Indicators 1 --Organizations 1 --shex schemas/webindex_NonRecursive.shex --allTypes --time"
paramsSingle="$params --no-scopeNodes"
paramsBad="$params --badComps 1"
$wiGenRunner $params >> $outName 
$wiGenRunner $paramsSingle >> $outName
$wiGenRunner $paramsBad >> $outName
done


