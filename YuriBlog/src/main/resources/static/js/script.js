const searchBar = document.getElementById('search-bar');
const searchResults = document.getElementById('search');

const contentData = [
  { title: '記事1', content: '記事1の内容がここに入ります。' },
  { title: '記事2', content: '記事2の内容がここに入ります。' },
  { title: '記事3', content: '記事3の内容がここに入ります。' },
  // 他のコンテンツも同様に追加
];

searchBar.addEventListener('input', function () {
  const searchTerm = searchBar.value.toLowerCase();
  const filteredResults = contentData.filter(item =>
    item.title.toLowerCase().includes(searchTerm) ||
    item.content.toLowerCase().includes(searchTerm)
  );

  displayResults(filteredResults);
});

function displayResults(results) {
  searchResults.innerHTML = '';

  if (results.length === 0) {
    searchResults.innerHTML = '<p>該当する結果はありません。</p>';
    return;
  }

  results.forEach(result => {
    const resultElement = document.createElement('div');
    resultElement.innerHTML = `<h3>${result.title}</h3><p>${result.content}</p>`;
    searchResults.appendChild(resultElement);
  });
}