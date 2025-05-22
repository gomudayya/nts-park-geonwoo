export function formatDateUtil(strTime) {
  const date = new Date(strTime);

  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, '0'); // 0-based
  const day = String(date.getDate()).padStart(2, '0');
  const hour = String(date.getHours()).padStart(2, '0'); // 0~23
  const minute = String(date.getMinutes()).padStart(2, '0');

  return `${year}.${month}.${day} ${hour}:${minute}`;
}
