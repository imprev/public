To interview:

1) pull latest code from master (will have all relevant files deleted)

git checkout master
git pull

2) create a new branch using git:

git checkout -b interview-[persons_name]

3) cherry pick and push relevant commit (58b905d38d6d4d4ccba86129104e8f8e26f2ecc9)

git cherry-pick 58b905d38d6d4d4ccba86129104e8f8e26f2ecc9
git push --set-upstream origin interview-[persons_name]

4) open a PR in github