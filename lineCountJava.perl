#!/usr/bin/perl

# map of method names to number of lines
%methods = ();

$line = 0;
$open = 0;
$start = 0;
$total = 0;
$count = 0;

while (<>) {
    # ignore comments and blank lines
    next if (m|^\s*//|) || (/^\s*$/);
    $line++;

    # is this line defining a class?
    if (($open == 0) && (/^\s*(public)?\s*class\s+(\w+)/)) {
	$className = $2;
    }
    # or a method?
    elsif (($open == 1) && (/^\s*(public|protected|private)?\s*\w*\s*\w*\s+(\w+)\s*\(.*\)[^;]*$/)) {
	$methodName = $2;
    }

    # starting a block
    if (/{/) {
	if ($open == 1) {
	    $start = $line;
	}
	$open++;
    }
    # ending a block
    if (/}/) {
	$open--;
 	# found end of method
	if ($open == 1) {
	   $current = ($line - 1) - $start;
	   $methods{"$className.$methodName"} = $current;
	   $total += $current;
	   $count++;
	}
    }
}

print "   $total lines in $count methods (average = ", $total / $count, ")\n";
print map "   $methods{$_} \t $_\n", sort keys %methods;
